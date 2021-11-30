package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {

    private JPanel mainPanel;
    private JFrame mainFrame;

    public void makeWindow() {
        mainPanel = new JPanel();
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton[][] mineField = new JButton[8][8];

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j] = new JButton();

                mainPanel.add(mineField[i][j]);
            }
        }
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setSize(350,350);
        mainFrame.setVisible(true);
    }
}
