package GUI;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Initializer {
    Window window;
    MineTable table;
    int[] difficulty = new int[4];
    private ButtonGroup checkBoxGroup;

    public void askDifficulty(){

        JFrame askFrame = new JFrame();
        JLabel chooseDiff = new JLabel("Choose game difficulty:");
        JCheckBox easy = new JCheckBox("Easy");
        JCheckBox medium = new JCheckBox("Medium");
        JCheckBox hard = new JCheckBox("Hard");
        JButton confirmButton = new JButton("OK");
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));
        checkBoxPanel.add(easy);
        checkBoxPanel.add(medium);
        checkBoxPanel.add(hard);
        checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(easy);
        checkBoxGroup.add(medium);
        checkBoxGroup.add(hard);
        askFrame.getContentPane().add(BorderLayout.NORTH, chooseDiff);
        askFrame.getContentPane().add(BorderLayout.CENTER, checkBoxPanel);
        askFrame.getContentPane().add(BorderLayout.SOUTH, confirmButton);
        askFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        askFrame.setSize(200, 170);
        askFrame.setVisible(true);

        easy.addItemListener(new DifficultyListener("easy"));
        medium.addItemListener(new DifficultyListener("medium"));
        hard.addItemListener(new DifficultyListener("hard"));
        confirmButton.addActionListener(new OKListener(this));
    }

    class DifficultyListener implements ItemListener {
        
        private String setting;
        DifficultyListener (String arg) {
            setting = arg;
        }

        public void itemStateChanged(ItemEvent e){
            switch(setting) { 
                case "easy":
                    difficulty[0] = 8;
                    difficulty[1] = 8;
                    difficulty[2] = 10;
                    difficulty[3] = 440360;
                    break;
                case "medium":
                    difficulty[0] = 12;
                    difficulty[1] = 12;
                    difficulty[2] = 24;
                    difficulty[3] = 660540;
                    break;
                case "hard":
                    difficulty[0] = 16;
                    difficulty[1] = 16;
                    difficulty[2] = 46;
                    difficulty[3] = 880720;
                    break;
            }
        }
    }

    class OKListener implements ActionListener {
        private Initializer ini;

        OKListener (Initializer ini) {
            this.ini = ini;
        }
        
        public void actionPerformed(ActionEvent a) {
            window = new Window(ini, difficulty);
            window.makeWindow();
            table = new MineTable(difficulty, window);

        }
    }
    public static void main(String[] args) {
        Initializer ini = new Initializer();
        ini.askDifficulty();
    }




    
}
