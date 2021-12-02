package GUI;

import javax.swing.*;

public class ClickedButton extends JButton {

    public ClickedButton() {
        JPanel revealedPanel = new JPanel();
        JLabel revealedLabel = new JLabel("M");
        revealedPanel.add(revealedLabel); 
    }
}
