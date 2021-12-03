package Fields;
import java.awt.event.*;
import javax.swing.JButton;

import GUI.*;
import Listeners.*;

public class MineField implements Field {
    private int x;
    private int y;
    private JButton[][] mineField;
    private Window window;
    private WhatsUnder whatsUnder;

    public MineField (int x, int y, Window window) {
        this.whatsUnder = WhatsUnder.MINE;
        this.x = x;
        this.y = y;
        this.window = window;
        this.mineField = window.getButtons();
        JButton button = mineField[x][y];
        button.addActionListener(new ConsequenceListener());
    }
 
    public WhatsUnder geWhatsUnder() {
        return whatsUnder;
    }

    class ConsequenceListener implements ActionListener {
        public void actionPerformed (ActionEvent a){
            //JButton[][] mineField = window.getButtons();
            //mineField[x][y] = new ClickedButton(whatsUnder);
            ClickedButton.click(mineField[x][y], whatsUnder);
            window.refreshMineField();
            System.out.println("Minefield clicked.");
        }
    }



}
