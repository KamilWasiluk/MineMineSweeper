package GUI;

import java.util.ArrayList;
import Fields.FieldType;
import java.awt.event.*;

public class MineTable {

    private GameButton[][] mineField = new GameButton[8][8];
    private Window window;
    private int minesQuantity = 10;
    private int[][] mines = {{0,0}, {1,1}, {2,7}, {3,0}, {4,5}, {5,5}, {2,4}, {7,7}, {7,6}, {5,7}};
    private int xSize;
    private int ySize;

    public void setSize(Window window) {
        xSize = 8;
        ySize = 8;
        this.window = window;
    }

    public MineTable (int x, int y, Window window) {
        this.xSize = x;
        this.ySize = y;
        this.window = window;

        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                mineField[i][j] = window.getButton(i, j);
                //System.out.println("button added: " + i + j);
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
        while(minesToPut !=0)  {
            int a = (int) (Math.random() * 7 + 1);
            int b = (int) (Math.random() * 7 + 1);
            int[] coordinates = {a, b};
            if(!list.contains(coordinates)) {
                mineField[a][b].setType(FieldType.MINE);
                minesToPut --;
            }
        }
        /*
        for(int i = 0; i < 8; i++) {
            int a = mines[i][0];
            int b = mines[i][1];
            mineField[a][b].setType(FieldType.MINE);
            System.out.println("Mines added to the gamefield: " + i);
        }
        */
    }

    private void generateAdjacentfields() {
        ArrayList<int[]> adjacent = this.getAdjacents();
        
        for(int i = 0; i < adjacent.size(); i ++) {
            int[] xy = adjacent.get(i);
            int x = xy[0];
            int y = xy[1];
            
            if(mineField[x][y].getType() != FieldType.MINE){
                mineField[x][y].setType(FieldType.ADJACENT);
                int mineQuantity = assumeMines(mineField[x][y]);
                mineField[x][y].setNumberOfMinesNeighbouring(mineQuantity);
                //System.out.println("Adjacent field added: " + x + " " + y);
            }
        }
    }

    private void generateEmptyFields() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(mineField[i][j].getType() == null) {
                    mineField[i][j].setType(FieldType.EMPTY);
                    //System.out.println("Emty field added: " + i + " " + j);
                }
            }
        }
    }

    public ArrayList<int[]> getAdjacents() {
    
        ArrayList<int[]> adjacent = new ArrayList<>();
        
        for(int i = 0; i < 8; i++) {
            int a = mines[i][0];
            int b = mines[i][1];

            for(int x = -1; x < 2; x++){
                for(int y = -1; y < 2; y++){
                    int[] xy = {a + x, b + y};
                    adjacent.add(xy);

                    //System.out.println("Adding new coordinates: " + xy[0] + " " + xy[1]);
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
            //System.out.println("mineQuantity cheched: " + x + " " + y);

            if(mineField[a][b].getType() == FieldType.MINE) {
                mineQuantity ++;

                //System.out.println("MineQuantity increased");

            }
        }
        return mineQuantity;
    }

    public class StartGameListener implements ActionListener {

        private boolean onceClicked;
        private int x;
        private int y;

        private StartGameListener(int x, int y) {
            this.x = x;
            this. y = y;
            this.onceClicked = false;
        }
        
        public void actionPerformed(ActionEvent a) {
            if (onceClicked == true) return;
            
            //Starts the clock
            Runnable continuousTimer = new Refresher();
            Thread timerThread = new Thread(continuousTimer);
            timerThread.start();
            System.out.println("Timer initiated.");
            //

            GameButton button = mineField[x][y];
            button.setType(FieldType.EMPTY);

            //Removes this listener from rest of the buttons as the game is already initiated
            for(GameButton[] buttonRow : mineField) {
                for(GameButton everyOtherButton : buttonRow) {
                    onceClicked = true;
                    if(everyOtherButton != button) {
                        everyOtherButton.removeActionListener(this);
                    }   
                }
            }
            //onceClicked = true;

                       /*
            int[][] initiallyRevealedCoordinates;
            int aboveRevealedArea = (int) (Math.random() * 3 + 1);
            int belowRevealedArea = (int) (Math.random() * 3 + 1);
            int leftRevealedArea = (int) (Math.random() * 3 + 1);
            int rightRevealedArea = (int) (Math.random() * 3 + 1);
            int howToFill = (int) Math.random();
            int initX = 0;
            int initY = 0;
            switch(howToFill) {
                case 0:
                    for(int i = x - belowRevealedArea; i <= x + aboveRevealedArea; i++) {
                        int lj = (int) (Math.random() * leftRevealedArea);
                        int rj = (int) (Math.random() * rightRevealedArea);

                        for(int j = y - lj; j <= y + rj; j++) {
                            initiallyRevealedCoordinates[initX][initY] = j;
                        }
                    }
            }

            horizontal {3, 7}
            vertical {2, 3}
            coordinates {[3,2], [4,2], [5,2], [6,2], [7,2], [3,3], [4, 3]... [7,3]}
            

            */

            generateNewGame( setInitiallyRevealedField());

        }

        private ArrayList<int[]> setInitiallyRevealedField() {
            ArrayList<int[]> list = new ArrayList<>();
            int[] horizontal = {x - (int) (Math.random() *2 + 1), x + (int) (Math.random() *2 + 1)}; 
            int[] vertical = {y - (int) (Math.random() *2 + 1), y + (int) (Math.random() *2 + 1)}; 
            //int[][] coordinates = new int[horizontal[1] - horizontal[0] + 1][vertical[1] - vertical[0]];
            for(int i = horizontal[0]; i <= horizontal[1]; i++) {
                for(int j = vertical[0]; j <= vertical[1]; j++) {
                    list.add(new int[]{i,j});
                }
            }
            return list;
        }
    }

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
