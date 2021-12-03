package GUI;

import javax.swing.*;
import Fields.WhatsUnder;
import java.awt.*;

public class Window {

    private JPanel minefieldPanel;
    private JFrame mainFrame;
    private JButton[][] mineField = new JButton[8][8];
    private JLabel gameTimer;
    private JPanel timerPanel;
    private WhatsUnder[][] mineTable;

    public void makeWindow() {

        minefieldPanel = new JPanel();
        mainFrame = new JFrame();
        timerPanel = new JPanel();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String timerText = "00 : 00";
        gameTimer = new JLabel(timerText);
        timerPanel.add(gameTimer);
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j] = new JButton("   ");
                minefieldPanel.add(mineField[i][j]);
            }
        }

        mainFrame.getContentPane().add(BorderLayout.CENTER, minefieldPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, timerPanel);
        mainFrame.setSize(440,360);
        mainFrame.setVisible(true);
        
        mineTable = new WhatsUnder[8][8];

    }

    public JButton[][] getButtons() {
        return mineField;
    }

    public void refreshTimer() {
        if(Timer.getCurrentSeconds() < 10 && Timer.getCurrentMinutes() < 10) {
            gameTimer.setText("0" + Timer.getCurrentMinutes() + " : " + "0" + Timer.getCurrentSeconds());
        }else if (Timer.getCurrentSeconds() < 10 && Timer.getCurrentMinutes() >= 10) {
            gameTimer.setText(Timer.getCurrentMinutes() + " : " + "0" + Timer.getCurrentSeconds());
        }else if (Timer.getCurrentSeconds() >= 10 && Timer.getCurrentMinutes() < 10) {
            gameTimer.setText("0" + Timer.getCurrentMinutes() + " : " + Timer.getCurrentSeconds());
        }else {
            gameTimer.setText(Timer.getCurrentMinutes() + " : " + Timer.getCurrentSeconds());
        }

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

    public WhatsUnder[][] getMineTable() {
        return mineTable;
    }

    public void insertMineTable(WhatsUnder u,  int x, int y) {
        mineTable[x][y] = u;
    }
}
