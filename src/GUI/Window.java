package GUI;

import javax.swing.*;
import Fields.FieldType;

import java.awt.*;
import java.awt.event.*;

public class Window {

    private JPanel minefieldPanel;
    private JFrame mainFrame;
    private JLabel gameTimer;
    private JLabel mineCountLabel;
    private JPanel upperPanel;
    private JPanel mineCountPanel;
    private FieldType[][] mineTable;
    private int minesQuantity;
    private int width;
    private int height;
    private GameButton[][] mineField; // = new GameButton[width][height];
    private JFrame gameOverFrame;
    private JButton gameOverButton;
    private JPanel gameOverPanel;
    private boolean isGameOver = false;
    private Initializer ini;
    private int[] size = new int[2];

    public Window(Initializer ini, int[] difficulty) {
        this.ini = ini;
        this.width = difficulty[0];
        this.height = difficulty[1];
        this.minesQuantity = difficulty[2];
        if(difficulty[3]==440360){
            size[0] = 440;
            size[1] = 360;
        }else if(difficulty[3]==660540){
            size[0] = 660;
            size[1] = 540;
        }else if(difficulty[3]==880720){
            size[0] = 880;
            size[1] = 720;
        }
    }

    public void makeWindow() {
        mineField = new GameButton[width][height];
        minefieldPanel = new JPanel(new GridLayout(width,height));
        mainFrame = new JFrame();
        upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.LINE_AXIS));

        mineCountPanel = new JPanel();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String timerText = "00 : 00";
        gameTimer = new JLabel(timerText);
        
        String minesCountdown = "Mines left: " + minesQuantity;
        mineCountLabel = new JLabel(minesCountdown);
        upperPanel.add(mineCountLabel);
        upperPanel.add(Box.createRigidArea(new Dimension (100, 0)));
        upperPanel.add(gameTimer);
        mineCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        gameTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                mineField[i][j] = new GameButton(i, j);
                minefieldPanel.add(mineField[i][j]);
            }
        }

        mainFrame.getContentPane().add(BorderLayout.CENTER, minefieldPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, upperPanel);
        mainFrame.setSize(size[0],size[1]);
        mainFrame.setVisible(true);
        mineTable = new FieldType[width][height];
    }

    public void changeMineQuantity(boolean a) {
        if(a){
            minesQuantity--;
        }else{
            minesQuantity++;
        }
        String minesCountdown = "Mines left: " + minesQuantity;
        mineCountLabel.setText(minesCountdown);

        mineCountLabel.repaint();
    }

    public GameButton getButton(int x, int y) {
        return  mineField[x][y];
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

    public FieldType[][] getMineTable() {
        return mineTable;
    }

    public void insertMineTable(FieldType u,  int x, int y) {
        mineTable[x][y] = u;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void congratulates(){
        closeWindow("You won. Congratulations!");

    }
    public void gameOver(){
        closeWindow("GameOver");
    } 
    
    private void closeWindow(String text) {
        gameOverFrame = new JFrame();
        gameOverFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gameOverButton = new JButton("OK");
        gameOverButton.addActionListener(new EndGameListener());
        JLabel gameOverLabel = new JLabel(text);
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.PAGE_AXIS));

        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(gameOverButton);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //gameOverPanel.CENTER_ALIGNMENT;
        gameOverFrame.getContentPane().add(BorderLayout.CENTER, gameOverPanel);
        gameOverFrame.setSize(200, 100);
        gameOverFrame.setVisible(true);
    }

    class EndGameListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            isGameOver = true;
            gameOverFrame.dispose();
            mainFrame.dispose();
            ini.askDifficulty();
        }
    }
}
