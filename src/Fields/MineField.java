package Fields;
import java.awt.event.*;
import javax.swing.JButton;

import GUI.*;

public class MineField implements Field, ActionListener{
    private int x;
    private int y;
    private JButton[][] mineField;
    private Window window;

    public MineField (int x, int y, Window window) {
        this.x = x;
        this.y = y;
        this.mineField = window.getButtons();
        //JButton button = mineField[x][y];
    }

    public void actionPerformed (ActionEvent a){
        mineField[x][y] = new ClickedButton();
        window.refreshMineField();
    }
}
