package Fields;

import javax.swing.JButton;
import java.awt.event.*;
import GUI.*;

public class FreeField implements Field {
   
    private int x;
    private int y;
    private JButton[][] mineField;
    private Window window;
    private WhatsUnder whatsUnder;

    public FreeField (int x, int y, Window window) {
        this.whatsUnder = WhatsUnder.EMPTY;
        this.x = x;
        this.y = y;
        this.window = window;
        this.mineField = window.getButtons();
        JButton button = mineField[x][y];
        button.addActionListener(new ConsequenceListener());
    }

    public WhatsUnder getWhatsUnder() {
        return whatsUnder;
    }

    class ConsequenceListener implements ActionListener {
        public void actionPerformed (ActionEvent a){
            //JButton[][] mineField = window.getButtons();
            ClickedButton.click(mineField[x][y], whatsUnder);
            //mineField[x][y] = new ClickedButton(whatsUnder);
            window.refreshMineField();
            System.out.println("Empty field clicked.");
        }
    }
}
