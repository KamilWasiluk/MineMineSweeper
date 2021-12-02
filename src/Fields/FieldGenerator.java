package Fields;

import javax.swing.JButton;

import GUI.Window;

public class FieldGenerator {
    
    public void generateGameField(Window window) {

        Field[][] gameField = new Field[8][8];
        int[][] mines = {{0,0}, {1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7}};
        
        for(int i = 0; i < 8; i++) {
            int a = mines[i][0];
            int b = mines[i][1];
            gameField[a][b] = new MineField(a, b, window);
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(gameField[i][j] == null) {
                    gameField[i][j] = new FreeField(i, j);
                }
            }
        }
    }
}
