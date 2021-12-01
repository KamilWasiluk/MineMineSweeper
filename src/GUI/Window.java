package GUI;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class Window {

    private JPanel mainPanel;
    private JFrame mainFrame;
    private JButton[][] mineField = new JButton[8][8];
    public JLabel gameTimer;
    private JPanel timerPanel;


    public void makeWindow() {
        mainPanel = new JPanel();
        mainFrame = new JFrame();
        timerPanel = new JPanel();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String timerText = "0 : 0";
        gameTimer = new JLabel(timerText);        
        timerPanel.add(gameTimer);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j] = new JButton();

                mainPanel.add(mineField[i][j]);
            }
        }
        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, timerPanel);
        mainFrame.setSize(350,350);
        mainFrame.setVisible(true);
    }

    public JButton[][] getButtons() {
        return mineField;
    }

    public void refreshTimer() {
        gameTimer.setText(Timer.getCurrentMinutes() + " : " + Timer.getCurrentSeconds());
        gameTimer.repaint();
    }

    
}
