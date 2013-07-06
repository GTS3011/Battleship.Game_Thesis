package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class MyShips extends JPanel implements ActionListener {

    private WarShip patrolShip = new WarShip(1, Color.LIGHT_GRAY);
    private WarShip destroyer = new WarShip(2, Color.LIGHT_GRAY);
    private WarShip submarine = new WarShip(3, Color.LIGHT_GRAY);
    private WarShip battleship = new WarShip(4, Color.LIGHT_GRAY);
    private WarShip aircraftCarrier = new WarShip(5, Color.LIGHT_GRAY);
    ImageIcon myFleetIcon = new ImageIcon("graphics/myFleetIcon.gif");
    JLabel myFleetLabel = new JLabel("", myFleetIcon, JLabel.CENTER);
    private int shipBlocks = 0;

    public MyShips() {

        setLayout(new GridLayout(6, 1));
        add(myFleetLabel);
        add(aircraftCarrier);
        add(battleship);
        add(submarine);
        add(destroyer);
        add(patrolShip);

        aircraftCarrier.setIcon(new ImageIcon("graphics/aircraftCarrier.gif"));
        battleship.setIcon(new ImageIcon("graphics/battleship.gif"));
        submarine.setIcon(new ImageIcon("graphics/submarine.gif"));
        destroyer.setIcon(new ImageIcon("graphics/destroyer.gif"));
        patrolShip.setIcon(new ImageIcon("graphics/patrolShip.gif"));

        // Add Action Listeners on WarShips.
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof WarShip) {
                ((WarShip) getComponent(i)).addActionListener(this);
            }
        }
    }

    /*
     * Determines the amount of seaBlocks to be captured on MyBoard class. Each WarShip has different size. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        WarShip shipSelected = (WarShip) e.getSource();
        if (shipSelected.getWarshipBlocks() == 1) {
            shipBlocks = 1;
        }
        if (shipSelected.getWarshipBlocks() == 2) {
            shipBlocks = 2;
        }
        if (shipSelected.getWarshipBlocks() == 3) {
            shipBlocks = 3;
        }
        if (shipSelected.getWarshipBlocks() == 4) {
            shipBlocks = 4;
        }
        if (shipSelected.getWarshipBlocks() == 5) {
            shipBlocks = 5;
        }
    }

    /**
     * If a WarShip completes its placement on the grid, its button must be
     * disabled.
     */
    public void setOnGrid() {
        switch (shipBlocks) {
            case (1):
                patrolShip.setEnabled(false);
                break;
            case (2):
                destroyer.setEnabled(false);
                break;
            case (3):
                submarine.setEnabled(false);
                break;
            case (4):
                battleship.setEnabled(false);
                break;
            case (5):
                aircraftCarrier.setEnabled(false);
                break;
        }
        validate();
    }

    //Getters & Setters :
    public int getShipBlocks() {
        return shipBlocks;
    }

    public void setShipBlocks(int shipBlocks) {
        this.shipBlocks = shipBlocks;
    }
}