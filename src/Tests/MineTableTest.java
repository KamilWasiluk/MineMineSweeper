package Tests;

import GUI.*;
import Fields.*;

public class MineTableTest {
    
    public static void main(String[] args) {
        Window window = new Window();
        window.makeWindow();
        MineTable table = new MineTable(8, 8, window);

    }
}
