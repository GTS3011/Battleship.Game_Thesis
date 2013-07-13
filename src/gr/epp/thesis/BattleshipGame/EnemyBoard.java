package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since 
 */
public class EnemyBoard extends JPanel implements MouseListener {

    private int rows = 10;
    private int columns = 10;
    private int[] coords = new int[3];
    private boolean enabledAll = false;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image target = toolkit.getImage("graphics/target.gif");
    Point cursorHotSpot = new Point(10, 10);

    public EnemyBoard() {

        setLayout(new GridLayout(rows, columns));
        setBackground(Color.WHITE);
        setEnabled(false);
        for (int i = 0; i < rows * columns; i++) {
            SeaBlock seaBlock = new SeaBlock();
            seaBlock.setPreferredSize(new Dimension(50, 50));
            seaBlock.setEnabled(false);
            add(seaBlock, i);
        }

        // PIRAMATIKO !! Random Battle Stations!!
        readyWarships();


    }

    /**
     * A method to create seaBlocks of EnemyBoard Panel. This method also adds
     * MouseListener to each SeaBlock.
     */
    /**
     * A method to create enemy's shipBlocks of EnemyBoard Panel. This method
     * also adds MouseListener to each ShipBlock.
     */
    public final ShipBlock createShipBlocks(int totalBlocks) {
        ShipBlock shipBlock = new ShipBlock(totalBlocks, 0, 0, false);
        shipBlock.setPreferredSize(new Dimension(50, 50));
        shipBlock.setEnabled(false);
        return shipBlock;
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

    /*
     * PIRAMATIKO.. Gia tis voles! Set enemy warships on enemy grid.
     */
    public void readyWarships() {
        for (int j = 21; j <= 25; j++) {
            remove(getComponent(j));
            add(createShipBlocks(5), j);
        }
        for (int j = 82; j <= 85; j++) {
            remove(getComponent(j));
            add(createShipBlocks(4), j);
        }
        for (int j = 37; j <= 57; j = j + rows) {
            remove(getComponent(j));
            add(createShipBlocks(3), j);
        }
        for (int j = 69; j <= 79; j = j + rows) {
            remove(getComponent(j));
            add(createShipBlocks(2), j);
        }
        remove(getComponent(42));
        add(createShipBlocks(1), 42);
    }

    /*
     * PIRAMATIKO2.. Gia tis voles!
     * Stelnei ta Strings. Hit Coordinates.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        getBlockPosition((JButton) e.getSource());
        JButton temp = (JButton) e.getSource();
        System.out.println("Firing @ : " + coords[0] + " - " + coords[1]);
        if (temp instanceof ShipBlock) {
            temp.setIcon(new ImageIcon("graphics/fire.gif"));
            System.out.println("hit!");
            temp.removeMouseListener(this);
        } else {
            temp.setIcon(new ImageIcon("graphics/miss.gif"));
            System.out.println("miss");
            temp.removeMouseListener(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /*
     * On MouseEneter the mouse cursor changes to target cursor. 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Cursor targetCursor = toolkit.createCustomCursor(target, cursorHotSpot, "Cursor");
        setCursor(targetCursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean isEnabledAll() {
        return enabledAll;
    }

    public void setEnabledAll(boolean enabledAll) {
        this.enabledAll = enabledAll;
        getParent().setEnabled(true);
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).addMouseListener(this);
            getComponent(i).setEnabled(true);
        }
    }
}