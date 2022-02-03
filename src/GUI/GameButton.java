package GUI;

import javax.swing.*;
import Fields.FieldType;
import java.awt.event.*;

public class GameButton extends JButton {

    private int rowIndex;
    private int columnIndex;
    private FieldType type;
    private int howManyMinesNeighbouring;

    public GameButton() {
        super();
    }

    public GameButton (int x, int y) {
        super();
        this.rowIndex = x;
        this.columnIndex = y;
        this.addActionListener(new ClickListener());
    }


    public void addAction() {
        this.addActionListener(new ClickListener());
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public FieldType getType() {
        if(type == null) return null;
        return type;
    }

    public void setXY(int x, int y) {
        this.rowIndex = x;
        this.columnIndex = y;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setNumberOfMinesNeighbouring (int num) {
        if(type != FieldType.ADJACENT) {
            return;
        }else {
            howManyMinesNeighbouring = num;
        }

    }

    class ClickListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            setEnabled(false);

            switch(type) {
            case MINE:
                mineAction();
                break;
            case ADJACENT:
                adjacentAction();
                break;
            case EMPTY:
                emptyAction();
                break;
            }
        }
        
        private void mineAction() {
            setText("M");
            //end the game
        }
 
        private void adjacentAction() {
            setText("" + howManyMinesNeighbouring);

        }
        
        private void emptyAction() {
            setText("   ");
            //reveal neighboring empty fields
        }
    }

}
