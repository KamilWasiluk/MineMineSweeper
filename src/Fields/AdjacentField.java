package Fields;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import GUI.*;

public class AdjacentField implements Field{
   
    private int x;
    private int y;
    private int mineQuantity;
    private JButton[][] mineField;
    private WhatsUnder whatsUnder;
    private Window window;

    public AdjacentField(int x, int y, Window window) {
        this.whatsUnder = WhatsUnder.ADJACENT;
        this.x = x;
        this.y = y;
        this.window = window;
        this.mineField = window.getButtons();
        JButton button = mineField[x][y];
        button.addActionListener(new ConsequenceListener());
    }

    public WhatsUnder getWhatsUnder() {
        return whatsUnder;
    }

    

    public class ConsequenceListener implements ActionListener {
        public void actionPerformed (ActionEvent a){
            //JButton[][] mineField = window.getButtons();
            ClickedButton.click(mineField[x][y], this.assumeMines());
            //mineField[x][y] = new ClickedButton(whatsUnder);
            window.refreshMineField();
            System.out.println("Mine is close");
        }

        public int assumeMines() {
            ArrayList<int[]> neighbouringMines = new ArrayList<>();
            System.out.println("Getting class of neighbourinMines array: " + mineField[0][0].getClass().getSimpleName());
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    int[] xy = {x + i, y + j};
                    neighbouringMines.add(xy);

                    //System.out.println("Adding new coordinates: " + xy[0] + " " + xy[1]);
                }
            }
            neighbouringMines.removeIf(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7);
            
            for(int[] crn : neighbouringMines) {
                int x = crn[0];
                int y = crn[1];
                System.out.println("mineQuantity cheched: " + x + y);

                if(window.getMineTable()[x][y] == WhatsUnder.MINE) {
                    mineQuantity ++;

                    System.out.println("MineQuantity increased");

                }
            }
            return mineQuantity;
        }
    }

}
