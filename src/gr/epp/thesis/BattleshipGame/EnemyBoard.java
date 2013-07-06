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
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class EnemyBoard extends JPanel implements MouseListener {

    private int rows = 10;
    private int columns = 10;
    private int[] coords = new int[3];
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image target = toolkit.getImage("graphics/target.gif");
    Point cursorHotSpot = new Point(10, 10);

    public EnemyBoard() {

        setLayout(new GridLayout(rows, columns));
        setBackground(Color.WHITE);
        for (int i = 0; i < rows * columns; i++) {
            add(createseaBlocks());
        }

        // PIRAMATIKO !!
        readyWarships();
    }

    /**
     * A method to create seaBlocks of EnemyBoard Panel. This method also adds
     * MouseListener to each SeaBlock.
     */
    public final SeaBlock createseaBlocks() {
        SeaBlock seaBlock = new SeaBlock(false);
        seaBlock.addMouseListener(this);
        seaBlock.setBackground(Color.CYAN);
        seaBlock.setPreferredSize(new Dimension(60, 60));
        return seaBlock;
    }

    /**
     * A method that instantly, gives each SeaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(SeaBlock pressedBlock) {
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
            SeaBlock temp = (SeaBlock) getComponent(j);
            temp.setIsShipOnIt(true);
        }
        for (int j = 82; j <= 85; j++) {
            SeaBlock temp = (SeaBlock) getComponent(j);
            temp.setIsShipOnIt(true);
        }
        for (int j = 37; j <= 57; j = j + rows) {
            SeaBlock temp = (SeaBlock) getComponent(j);
            temp.setIsShipOnIt(true);
        }
        for (int j = 69; j <= 79; j = j + rows) {
            SeaBlock temp = (SeaBlock) getComponent(j);
            temp.setIsShipOnIt(true);
        }
        SeaBlock temp = (SeaBlock) getComponent(42);
        temp.setIsShipOnIt(true);
    }

    /*
     * PIRAMATIKO2.. Gia tis voles!
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        getBlockPosition((SeaBlock) e.getSource());
        SeaBlock temp = (SeaBlock) e.getSource();
        System.out.println("Coordinates : " + coords[2]);
        if (temp.isIsShipOnIt()) {
            temp.setIcon(new ImageIcon("graphics/fire.gif"));
            System.out.println("HIT!");
        } else {
            System.out.println("MISS!!");
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
}