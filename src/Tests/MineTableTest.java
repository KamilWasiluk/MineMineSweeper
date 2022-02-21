/*package Tests;

import GUI.*;
import Fields.*;

public class MineTableTest {
    Window window;
    MineTable table;
    int[] difficulty = new int[4];
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J"); // Clears the terminal
        MineTableTest test =  new MineTableTest();
        test.setDifficulty("medium");
        test.restart(test);
    }

    public void restart(MineTableTest test) { 
        test.window = new Window(test, difficulty);
        test.window.makeWindow();
        test.table = new MineTable(difficulty, window);
    }

    private void setDifficulty(String setting) {
        switch(setting){
            case "easy":
                difficulty[0] = 8;
                difficulty[1] = 8;
                difficulty[2] = 10;
                difficulty[3] = 440360;
                break;
            case "medium":
                difficulty[0] = 12;
                difficulty[1] = 12;
                difficulty[2] = 24;
                difficulty[3] = 660540;
                break;
            case "hard":
                difficulty[0] = 16;
                difficulty[1] = 16;
                difficulty[2] = 46;
                difficulty[3] = 880720;
                break;
        }
    }
}*/