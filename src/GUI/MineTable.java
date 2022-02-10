package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import Fields.FieldType;
import java.awt.event.*;

public class MineTable {

    private GameButton[][] mineField = new GameButton[8][8]; 
    private Window window;
    private int minesQuantity = 10;
    private ArrayList<int[]> mineCoordinates = new ArrayList<int[]>();
    private int mineFieldWith;
    private int mineFieldHeight;
    private boolean isGameInitiatedChecker = false;     //Checks condition for the listener starting the game to be activated or removed

    public void setSize(Window window) {
        mineFieldWith = 8;
        mineFieldHeight = 8;
        this.window = window;
    }

    public MineTable (int x, int y, Window window) {
        this.mineFieldWith = x;
        this.mineFieldHeight = y;
        this.window = window;

        //imports the 2d array of gamebuttons from Window class
        for(int i = 0; i < mineFieldWith; i++) {
            for(int j = 0; j < mineFieldHeight; j++) {
                mineField[i][j] = window.getButton(i, j);
                mineField[i][j].setMineTable(this);
                mineField[i][j].addActionListener(new StartGameListener(i, j));
            }
        }
    }

    public GameButton getButton(int x, int y) {
        return mineField[x][y];
    }

    public void generateNewGame(ArrayList<int[]> list) {
        
        generateMineFields(list);
        generateAdjacentfields();
        generateEmptyFields();
    }
        
    private void generateMineFields(ArrayList<int[]> list) {
        
        int minesToPut = minesQuantity;
        boolean collidingCoordinates = false;
        while(minesToPut > 0)  {
            int a = (int) (Math.random() * 8);
            int b = (int) (Math.random() * 8);
            int[] coordinates = {a, b};
            
            //check if mine coordinates don't belong to area revealed on the firs click
            for(int[] excludedCoor : list){
                if(Arrays.equals(excludedCoor, coordinates)) {
                collidingCoordinates = true;  
                break;
                } else{ 
                    collidingCoordinates = false;
                }
            }

            //check if mine coordinates aren't repeating
            for(int[] exludedCoor : mineCoordinates) {
                if(Arrays.equals(exludedCoor, coordinates)) {
                    collidingCoordinates = true;
                    break;
                }
            }

            if(collidingCoordinates == false) {
                mineField[a][b].setType(FieldType.MINE);
                mineCoordinates.add(coordinates);

                minesToPut --;
            }
        }
    }

    private void generateAdjacentfields() {

        ArrayList<int[]> adjacent = this.getAdjacents();
        
        for(int i = 0; i < adjacent.size(); i ++) {
            int[] xy = adjacent.get(i);
            int x = xy[0];
            int y = xy[1];
            
            if(mineField[x][y].getType() != FieldType.MINE){
                mineField[x][y].setType(FieldType.ADJACENT);
                int neighbouringMinesQuantity = assumeMines(mineField[x][y]);
                mineField[x][y].setNumberOfMinesNeighbouring(neighbouringMinesQuantity);
            }
        }
    }

    private void generateEmptyFields() {
        for(int i = 0; i < mineFieldWith; i++) {
            for(int j = 0; j < mineFieldHeight; j++) {
                if(mineField[i][j].getType() != FieldType.MINE && mineField[i][j].getType() != FieldType.ADJACENT) {
                    mineField[i][j].setType(FieldType.EMPTY);
                }
            }
        }
    }

    public ArrayList<int[]> getAdjacents() {
    
        ArrayList<int[]> adjacent = new ArrayList<>();
        
        for(int i = 0; i < mineCoordinates.size(); i++) {
            int[] mine = mineCoordinates.get(i);
            int a = mine[0];
            int b = mine[1];

            for(int x = -1; x < 2; x++){
                for(int y = -1; y < 2; y++){
                    int[] xy = {a + x, b + y};
                    adjacent.add(xy);
                }
            }
        }
        adjacent.removeIf(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7);
        return adjacent;
    }

    private int assumeMines(GameButton button) {
        int mineQuantity = 0;
        int x = button.getRowIndex();
        int y = button.getColumnIndex();
        ArrayList<int[]> neighbouringMines = new ArrayList<>();

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int[] xy = {x + i, y + j};
                neighbouringMines.add(xy);
            }
        }
        neighbouringMines.removeIf(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7);
        
        for(int[] crn : neighbouringMines) {
            int a = crn[0];
            int b = crn[1];

            if(mineField[a][b].getType() == FieldType.MINE) {
                mineQuantity ++;
            }
        }
        return mineQuantity;
    }

    //This listener initiates the game after first left click on any button. Generates mines' coordinates, starts timer and erases itself after.
    public class StartGameListener implements ActionListener {

        private int x;
        private int y;

        private StartGameListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void actionPerformed(ActionEvent a) {
            if (isGameInitiatedChecker == true) return;
            isGameInitiatedChecker = true;

            //Starts the clock
            Runnable continuousTimer = new Refresher();
            Thread timerThread = new Thread(continuousTimer);
            timerThread.start();
            System.out.println("Timer initiated.");
            
            //imports GameButton object clicked by player, adds GB class listeners to the object and activates left-click action for that button
            GameButton button = mineField[x][y];
            button.setType(FieldType.EMPTY);
            System.out.println("Button clicked " + x + " " + y);
            //button.addButtonListeners();
            GameButton.ClickListener click = button.new ClickListener();
            //click.actionPerformed(a);

            //Removes this listener from rest of the buttons as the game is already initiated, add listeners defined in GameButton class
            int i = 1;
            for(GameButton[] buttonRow : mineField) {
                for(GameButton everyOtherButton : buttonRow) {
                    if(everyOtherButton != button) {
                        everyOtherButton.removeActionListener(this);
                        everyOtherButton.addButtonListeners();
                    }   
                }
            }

            generateNewGame( setInitiallyRevealedField());
            click.actionPerformed(a);
        }

        //generates random area revealed with the first click
        private ArrayList<int[]> setInitiallyRevealedField() {
            ArrayList<int[]> list = new ArrayList<>();
            int[] horizontal = {x - (int) (Math.random() *2 + 1), x + (int) (Math.random() *2 + 1)}; 
            int[] vertical = {y - (int) (Math.random() *2 + 1), y + (int) (Math.random() *2 + 1)}; 
            
            for(int i = horizontal[0]; i <= horizontal[1]; i++) {
                for(int j = vertical[0]; j <= vertical[1]; j++) {
                    list.add(new int[]{i,j});
                    System.out.println("initially revealed fields added: " + i + " " + j); 
                }
            }
            return list;
        }
    }

    //Timer thread class
    class Refresher implements Runnable {

        public void run() {
            Timer.startTimer();

            while(true) {
                window.refreshTimer();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }
}
