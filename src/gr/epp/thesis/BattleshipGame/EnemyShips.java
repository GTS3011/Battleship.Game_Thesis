package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class EnemyShips extends JPanel {

    private WarShip patrolShip = new WarShip(1, Color.DARK_GRAY);
    private WarShip destroyer = new WarShip(2, Color.DARK_GRAY);
    private WarShip submarine = new WarShip(3, Color.DARK_GRAY);
    private WarShip battleship = new WarShip(4, Color.DARK_GRAY);
    private WarShip aircraftCarrier = new WarShip(5, Color.DARK_GRAY);
    ImageIcon enemyFleetIcon = new ImageIcon("graphics/enemyFleetIcon.gif");
    JLabel enemyFleetLabel = new JLabel("", enemyFleetIcon, JLabel.CENTER);

    public EnemyShips() {
        setLayout(new GridLayout(6, 1));
        add(enemyFleetLabel);
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
    }
}