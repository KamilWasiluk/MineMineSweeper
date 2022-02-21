package GUI;

import javax.swing.*;
import java.awt.*;

public class GButtonTest {

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        JPanel buttonPanel = new JPanel(new GridLayout(8,8));
        GameButton[][] buttonTable = new GameButton[8][8];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                //buttonTable[i][j] = new GameButton(i, j);
                //buttonTable[i][j].setText(i + " " + j);
                buttonPanel.add(new GameButton(i, j));

            }
        }

/*
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                buttonPanel.add(buttonTable[i][j]);
            }
        }
*/
        frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        frame.setSize(440, 360);
        frame.setVisible(true);
    }
}
