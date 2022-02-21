package GUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import Fields.FieldType;
import java.awt.event.*;

public class GameButton extends JButton {

    private int rowIndex;
    private int columnIndex;
    private FieldType type;
    private int howManyMinesNeighbouring;
    private MineTable mineTable;
    private ClickListener clickListener;
    boolean isEnabled;

    public GameButton() {
        super();
        this.isEnabled = true;
    }

    public GameButton (int x, int y) {
        super();
        this.rowIndex = x;
        this.columnIndex = y;
        this.isEnabled = true;
        //this.addactionlistener(new clicklistener());
        //this.addmouselistener(new mineflagger());
    }

    public void addButtonListeners() {
        this.addActionListener(new ClickListener());
        this.addMouseListener(new MineFlagger());
    }

    private void pointToClickListener(ClickListener cliked) {
        this.clickListener = cliked;
    }

    public ClickListener getClickListener() {
        return clickListener;
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

    public void setMineTable(MineTable min){
        this.mineTable = min;
    }
    
    private void showNeighbours(ActionEvent e){
        //mineTable.checkClickedEmptyButton(this);

        for(int i = rowIndex - 1; i <= rowIndex + 1; i++){
            for(int j = columnIndex -1; j <= columnIndex + 1; j++){
                if(i < 0 || j < 0 || i > mineTable.getWith() - 1 || j > mineTable.getHeight() - 1){}else{ 
                    GameButton button = mineTable.getButton(i, j);
                    
                    if(button.isEnabled == true &&  (button.getType() == FieldType.ADJACENT || button.getType() == FieldType.EMPTY)) {
                        button.getClickListener().actionPerformed(e);
                        System.out.println("neighbouring fields coordinates " +  button.getRowIndex() + " " + button.getColumnIndex());
                    }
                }
            }
        }
        System.out.println("checking neighbours");
    }

    //This listener set action after left click on a button

    class ClickListener implements ActionListener {
        
        ClickListener() {
            pointToClickListener(this);
        }
        
        public void actionPerformed (ActionEvent e) {
            setEnabled(false);
            isEnabled = false;

            switch(type) {
            case MINE:
                mineAction();
                break;
            case ADJACENT:
                adjacentAction();
                break;
            case EMPTY:
                emptyAction(e);
                break;            
            case MINE_SET:
                break;
            }

            mineTable.checkIfWon();
        }
        
        private void mineAction() {
            setText("M");
            setBackground(new ColorUIResource(51, 51, 153));
            mineTable.endGame(false);
        }
 
        private void adjacentAction() {
            setText("" + howManyMinesNeighbouring);
        }
        
        private void emptyAction(ActionEvent e) {
            setText("   ");
            showNeighbours(e);
            //reveal neighboring empty fields
        }

        
    }

    //This listener flags a mine when player right clicks a button where he expexts a mine. Another right click de-flags the button.

    class MineFlagger extends MouseAdapter {

        boolean pressed = false;
        boolean flagSet = false;
        FieldType previousType;

        @Override
         public void mousePressed(java.awt.event.MouseEvent e) {

            pressed = true;
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {

            if(!isEnabled){
                return;
            }
            
            if (pressed && type != FieldType.MINE_SET) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    setFlag();
                    mineTable.minesQuantityHasChanged(true);
                    mineTable.checkIfWon();
                    pressed = false;
                    
                } 
            } else if(pressed && type == FieldType.MINE_SET) {
                removeFlag();
                mineTable.minesQuantityHasChanged(false);

                pressed = false;
            }
        }

        private void setFlag() {
            setText("?");
            setEnabled(false);
            isEnabled = false;
            setBackground(new ColorUIResource(0, 153, 255));
            previousType = getType();
            setType(FieldType.MINE_SET);
            flagSet = true;
        }

        private void removeFlag() {
            setText("   ");
            setEnabled(true);
            isEnabled = true;
            setBackground(null);
            setType(previousType);
        }
    }

}
