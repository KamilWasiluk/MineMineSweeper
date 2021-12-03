package GUI;

import javax.swing.*;

import Fields.WhatsUnder;

public class ClickedButton {
    private WhatsUnder under;
    private JButton button;
    
    public static void click(JButton button, WhatsUnder under) {
        //this.under = under;
        //this.button = button;
        button.setEnabled(false);

        switch(under) {
            case MINE: button.setText("M");
                break;
            case EMPTY: button.setText("   ");
                break;
            //case ADJACENT: button.setText("A");
            //    break;       
            default:
                break;     
        }
    }

    public static void click(JButton button, int howMany) {
        button.setEnabled(false);
        String t = " "+howMany;
        button.setText(t);
    }
}
