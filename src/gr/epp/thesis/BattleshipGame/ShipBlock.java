package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author vigos.ioannis
 */
public final class ShipBlock extends JButton {

    private int blockID = 0;
    private int reset;

    public ShipBlock(int shipBlocks, int orientation, int currBlock, boolean ownShip) {

        if (ownShip) {
            setIcon(new ImageIcon("graphics/gridPieces/" + shipBlocks + "_" + currBlock + "_" + orientation + ".gif"));
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.CYAN);
        }
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }
}
