package Fields;

import java.util.ArrayList;

import GUI.Window;

public class FieldGenerator {
    private Window window;
    private Field[][] gameField;
    private int[][] mines = {{0,0}, {1,1}, {2,7}, {3,0}, {4,5}, {5,5}, {2,4}, {7,7}, {7,6}, {5,7}};

    public FieldGenerator(Window window) {
        this.window = window;
    }
    public void generateGameField() {

        gameField = new Field[8][8];
                       
        for(int i = 0; i < 8; i++) {
            int a = mines[i][0];
            int b = mines[i][1];
            gameField[a][b] = new MineField(a, b, window);
            window.insertMineTable(WhatsUnder.MINE, a, b); 
            //System.out.println("Mines added to the gamefield: " + i);
        }

        generateAdjacentfields();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(gameField[i][j] == null) {
                    gameField[i][j] = new FreeField(i, j, window);
                    window.insertMineTable(WhatsUnder.EMPTY, i, j); 
                }
                System.out.println("Empty fields added to the minefield.");
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
        //adjacent.forEach(i -> System.out.println("Updated coordinates: " + i[0] + " " + i[1]));
        return adjacent;
    }

    private void generateAdjacentfields() {
        ArrayList<int[]> adjacent = this.getAdjacents();
        
        for(int i = 0; i < adjacent.size(); i ++) {
            int[] xy = adjacent.get(i);
            int x = xy[0];
            int y = xy[1];
            
            if(gameField[x][y] == null){
                gameField[x][y] = new AdjacentField(x, y, window);
                window.insertMineTable(WhatsUnder.ADJACENT, x, y);
                System.out.println("Adjacent field added: " + x + " " + y);
            }
        }
    }
}
