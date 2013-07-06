package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class MyBoard extends JPanel implements MouseListener {

    private int rows = 10;
    private int columns = 10;
    private int shipBlocks = 0;
    private int[] coords = new int[3];
    private int orientation = 3;
    private boolean horizontal = true;
    private int tempHold = 0;
    private boolean shipOnGrid = false;

    public MyBoard() {
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.WHITE);
        for (int i = 0; i < rows * columns; i++) {
            add(createseaBlocks());
        }
    }

    /**
     * A method to create seaBlocks of MyBoard Panel. This method also adds
     * MouseListener to each SeaBlock.
     */
    public final SeaBlock createseaBlocks() {
        SeaBlock seaBlock = new SeaBlock(false);
        seaBlock.addMouseListener(this);
        seaBlock.setBackground(Color.CYAN);
        seaBlock.setPreferredSize(new Dimension(50, 50));
        return seaBlock;
    }

    /**
     * A method that instantly, gives each SeaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(JButton pressedBlock) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) == pressedBlock) {
                coords[0] = i / rows;
                coords[1] = i % columns;
                coords[2] = i;
                break;
            }
        }
    }

    /**
     * A method that contains all interactions with the grid. Those are,
     * Hovering on seaBlocks, Exiting from seaBlocks seablock, and Capturing.
     * This method needs the orientation of the ship placement, and also two
     * arguments for the behavior of the WarShip before the capture. If Hovering
     * is true, then a ship is above buttons. If Exiting is true, then the
     * SeaBlock, has to repaint itself because the WarShip is elsewhere. Values
     * of 3 & 6, about orientation means orientation of the WarShip clockwise.
     */
    public void battleFormations(int orientation, boolean hovering, boolean exiting) {
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        getComponent(coords[2] + i).setBackground(Color.GREEN);
                    } else if (exiting) {
                        getComponent(coords[2] + i).setBackground(Color.CYAN);
                    } else {
                        getComponent(coords[2] + i).setBackground(Color.DARK_GRAY);                 
                        ((JButton) getComponent(coords[2] + i)).removeMouseListener(this);
                    }
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocks; i++) {
                    if (hovering) {
                        getComponent(coords[2] + (i * rows)).setBackground(Color.GREEN);
                    } else if (exiting) {
                        getComponent(coords[2] + (i * rows)).setBackground(Color.CYAN);
                    } else {
                        getComponent(coords[2] + (i * rows)).setBackground(Color.DARK_GRAY);
                        ((JButton) getComponent(coords[2] + (i * rows))).removeMouseListener(this);
                    }
                }
                break;
        }
    }

    /**
     * A method for collision detection. This method prevents the installation
     * of another WarShip, that collides on the first one. Values of 3 & 6,
     * about orientation means orientation of the WarShip clockwise.
     */
    public boolean checkCollision(int orientation) {
        boolean freeArea = true;
        switch (orientation) {
            case (3):
                for (int i = coords[2]; i < (coords[2] + shipBlocks); i++) {
                    if (getComponent(i).getBackground() == Color.DARK_GRAY) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (rows * shipBlocks)); i = i + rows) {
                    if (getComponent(i).getBackground() == Color.DARK_GRAY) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
        }
        return false;
    }

    /*
     * On right click, the orientation changes from 3 to 6 and reverse. Logic
     * based on the method of clock. On left click, the battleFormations method
     * is called, after checking collision detection, always with proportional
     * orientation. The Hover and Exit Hover process also called from here.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseExited(e);
            horizontal ^= true;
            if (horizontal) {
                orientation = 3;
                mouseEntered(e);
            } else {
                orientation = 6;
                mouseEntered(e);
            }
            validate();
        } else {
            getBlockPosition((JButton) e.getSource());
            switch (orientation) {
                case (3):
                    if (coords[1] < (columns - (shipBlocks - 1)) && tempHold != shipBlocks) {
                        if (checkCollision(3)) {
                            battleFormations(3, false, false);
                            tempHold = shipBlocks;
                            shipOnGrid = true;
                        }
                    }
                    break;
                case (6):
                    if (coords[0] < rows - (shipBlocks - 1) && tempHold != shipBlocks) {
                        if (checkCollision(6)) {
                            battleFormations(6, false, false);
                            tempHold = shipBlocks;
                            shipOnGrid = true;
                        }
                    }
                    break;
            }
            validate();
        }
    }

    /* 
     * Code about the Hover effect. On Mouse Enter, and with the proper orientation, the grid paints so many SeaBlocks as many as the number of ShipsBlocks.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        getBlockPosition((JButton) e.getSource());
        switch (orientation) {
            case (3):
                if (coords[1] < (columns - (shipBlocks - 1)) && tempHold != shipBlocks) {
                    if (checkCollision(3)) {
                        battleFormations(3, true, false);
                    }
                }
                break;
            case (6):
                if (coords[0] < rows - (shipBlocks - 1) && tempHold != shipBlocks) {
                    if (checkCollision(6)) {
                        battleFormations(6, true, false);
                    }
                }
                break;
        }
        validate();
    }

    /* 
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        getBlockPosition((JButton) e.getSource());
        switch (orientation) {
            case (3):
                if (coords[1] < (columns - (shipBlocks - 1))) {
                    if (checkCollision(3)) {
                        battleFormations(3, false, true);
                    }
                }
                break;
            case (6):
                if (coords[0] < rows - (shipBlocks - 1)) {
                    if (checkCollision(6)) {
                        battleFormations(6, false, true);
                    }
                }
                break;
        }
        validate();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // Getters & Setters
    public int getShipBlocks() {
        return shipBlocks;
    }

    public void setShipBlocks(int shipBlocks) {
        this.shipBlocks = shipBlocks;
    }

    public boolean isShipOnGrid() {
        return shipOnGrid;
    }

    public void setShipOnGrid(boolean shipOnGrid) {
        this.shipOnGrid = shipOnGrid;
    }
}