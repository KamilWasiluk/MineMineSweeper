package Tests;

import GUI.*;
import java.awt.event.*;

import javax.swing.JButton;

public class GUITest {

    static Window window = new Window();
    public static void main(String[] args) {
        GUITest test = new GUITest();
        //Window window = new Window();
        window.makeWindow();
        JButton[][] mineField = window.getButtons();
        /*
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j].addActionListener(test.new StartTimer());
            }
        }
        */
        mineField[0][0].addActionListener(test.new StartTimer());
    }

    class StartTimer implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            Runnable continuousTimer = new Refresher();
            Thread timerThread = new Thread(continuousTimer);
            timerThread.start();
            System.out.println("Timer initiated.");
        }
    }

    class Refresher implements Runnable {

        public void run() {
            Timer.startTimer();

            while(true) {
                window.refreshTimer();
                System.out.println(window.gameTimer.getText());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    } 
}
