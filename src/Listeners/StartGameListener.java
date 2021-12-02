package Listeners;
import java.awt.event.*;
import javax.swing.*;
import Fields.FieldGenerator;
import GUI.ClickedButton;
import GUI.Timer;
import GUI.Window;

public class StartGameListener implements ActionListener {

    private Window window;
    private int x;
    private int y;
    private static boolean onceClicked = false;
    
    public StartGameListener(Window window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
    }
    
    public void actionPerformed(ActionEvent a) {
        if (onceClicked == true) return;

        Runnable continuousTimer = new Refresher();
        Thread timerThread = new Thread(continuousTimer);
        timerThread.start();
        System.out.println("Timer initiated.");
        JButton button = window.getButtons()[x][y];

        for(JButton[] buttonRow : window.getButtons()) {
            for(JButton everyOtherButton : buttonRow) {
                if(everyOtherButton != button) {
                    everyOtherButton.removeActionListener(this);
                }                
            }
        }
        
        FieldGenerator f = new FieldGenerator(window);
        f.generateGameField();
        ClickedButton.click(button, f.getWhatsUnder(x, y));
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
