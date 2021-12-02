package GUI;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class Window {

    private JPanel minefieldPanel;
    private JFrame mainFrame;
    private JButton[][] mineField = new JButton[8][8];
    private JLabel gameTimer;
    private JPanel timerPanel;


    public void makeWindow() {

        minefieldPanel = new JPanel();
        mainFrame = new JFrame();
        timerPanel = new JPanel();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String timerText = "0 : 0";
        gameTimer = new JLabel(timerText);
        timerPanel.add(gameTimer);
        //GridBagConstraints c = new GridBagConstraints();
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j] = new JButton("   ");
                /*
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = j;
                c.gridwidth = 1;
                c.gridheight =1;
                */
                minefieldPanel.add(mineField[i][j]);
            }
        }

        mainFrame.getContentPane().add(BorderLayout.CENTER, minefieldPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, timerPanel);
        mainFrame.setSize(440,360);
        mainFrame.setVisible(true);
    }

    public JButton[][] getButtons() {
        return mineField;
    }

    public void refreshTimer() {
        gameTimer.setText(Timer.getCurrentMinutes() + " : " + Timer.getCurrentSeconds());
        gameTimer.repaint();
    }

    public void refreshMineField(){
        minefieldPanel.removeAll();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                minefieldPanel.add(mineField[i][j]);
            }
        }
        minefieldPanel.repaint();
    }

    
}
