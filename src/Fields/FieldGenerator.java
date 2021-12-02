package Fields;

import GUI.Window;

public class FieldGenerator {
    private Window window;
    private Field[][] gameField;
    private WhatsUnder[][] mineTable;
    public FieldGenerator(Window window) {
        this.window = window;
    }
    public void generateGameField() {

        gameField = new Field[8][8];
        mineTable = new WhatsUnder[8][8];
        int[][] mines = {{0,0}, {1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7}};
        
        for(int i = 0; i < 8; i++) {
            int a = mines[i][0];
            int b = mines[i][1];
            gameField[a][b] = new MineField(a, b, window);
            mineTable[a][b] = WhatsUnder.MINE;
            //System.out.println("Mines added to the gamefield: " + i);
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(gameField[i][j] == null) {
                    gameField[i][j] = new FreeField(i, j, window);
                    mineTable[i][j] = WhatsUnder.EMPTY;
                }
                //System.out.println("Empty fields added to the minefield.");
            }
        }
    }

    public WhatsUnder getWhatsUnder(int x, int y) {
        return mineTable[x][y];
    }
}
