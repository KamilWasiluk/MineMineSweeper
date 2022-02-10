package Tests;

import GUI.*;
import Fields.*;

public class MineTableTest {
    
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J"); // Clears the terminal
        Window window = new Window();
        window.makeWindow();
        MineTable table = new MineTable(8, 8, window);
        

    }
}