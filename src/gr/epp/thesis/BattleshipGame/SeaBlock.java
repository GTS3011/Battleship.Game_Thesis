package gr.epp.thesis.BattleshipGame;

import javax.swing.JButton;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class SeaBlock extends JButton {

    private boolean isShipOnIt = false;

    public SeaBlock(boolean isShipOnIt) {
        this.isShipOnIt = isShipOnIt;
    }

    public final boolean isIsShipOnIt() {
        return isShipOnIt;
    }

    /**
     * If a seaBlock goes to be part of a WarShip, this is set to true.
     */
    public final void setIsShipOnIt(boolean isShipOnIt) {
        this.isShipOnIt = isShipOnIt;
    }
}