package GUI;
import java.awt.event.*;

public class StartTimerListener implements ActionListener {

    private Window window;
    private static boolean onceClicked = false;
    
    public StartTimerListener(Window window) {
        this.window = window;
    }
    
    public void actionPerformed(ActionEvent a) {
        if (onceClicked == true) return;

        Runnable continuousTimer = new Refresher();
        Thread timerThread = new Thread(continuousTimer);
        timerThread.start();
        System.out.println("Timer initiated.");
        onceClicked = true;

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
