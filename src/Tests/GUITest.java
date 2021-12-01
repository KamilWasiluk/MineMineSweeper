package Tests;

import GUI.*;
//import java.awt.event.*;

import javax.swing.JButton;

public class GUITest {

    static Window window = new Window();
    public static void main(String[] args) {
        GUITest test = new GUITest();
        //Window window = new Window();
        window.makeWindow();
        JButton[][] mineField = window.getButtons();
       
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                mineField[i][j].addActionListener(new StartTimerListener(window));
            }
        }
       
        //mineField[0][0].addActionListener(new StartTimerListener(window));
    }

     
}
