package GUI;
import java.awt.event.*;

import Fields.FieldGenerator;

public class StartGameListener implements ActionListener {

    private Window window;
    private static boolean onceClicked = false;
    
    public StartGameListener(Window window) {
        this.window = window;
    }
    
    public void actionPerformed(ActionEvent a) {
        if (onceClicked == true) return;

        Runnable continuousTimer = new Refresher();
        Thread timerThread = new Thread(continuousTimer);
        timerThread.start();
        System.out.println("Timer initiated.");
        new FieldGenerator().generateGameField(window);
        onceClicked = true;

    }

    class Refresher implements Runnable {

        public void run() {
            Timer.startTimer();

            while(true) {
                window.refreshTimer();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }
}   
