package gr.epp.thesis.BattleshipGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */
public final class Battleship_Game extends JFrame {

    private JPanel upPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel decorPanel = new JPanel();
    private MyShips myShips = new MyShips();
    private EnemyShips enemyShips = new EnemyShips();
    private MyBoard myBoard = new MyBoard();
    private EnemyBoard enemyBoard = new EnemyBoard();
    ImageIcon decorImage = new ImageIcon("graphics/bismarck.png");
    JLabel decorLabel = new JLabel("", decorImage, JLabel.CENTER);
    private int tempCounter = 0;
    Server server = new Server();
    private EnemyPlayer_Test virtualEnemy = new EnemyPlayer_Test();

    public Battleship_Game() {

        // All graphic contents of the game.
        setLayout(new GridLayout(3, 1, 0, 5));
        setBackground(Color.WHITE);

        add(upPanel);
        upPanel.setBackground(Color.WHITE);

        add(decorPanel);
        decorPanel.setBackground(Color.WHITE);
        decorPanel.add(decorLabel, BorderLayout.CENTER);

        add(downPanel);
        downPanel.setBackground(Color.WHITE);

        upPanel.setLayout(new BorderLayout(10, 0));
        upPanel.add(enemyBoard, BorderLayout.CENTER);
        upPanel.add(enemyShips, BorderLayout.WEST);

        downPanel.setLayout(new BorderLayout(10, 0));
        downPanel.add(myBoard, BorderLayout.CENTER);
        downPanel.add(myShips, BorderLayout.EAST);

        setTitle("Battleship");
        setSize(450, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        validate();

        /**
         * Controls the flow of the game before battle. When all ships are on
         * grid, the game attempts to start.
         */
        if (ownBattleStations()) {
            ownTurn(true);
        } else {
            myBoard.enemysFire(virtualEnemy.enemyFire());
        }

        validate();
    }

    /**
     *
     * @return
     */
    public boolean ownTurn(boolean ownTurn) {
    }

    /**
     * PROXIRO!! Elegxei an exoun stithei ta ploia stin myBoard. Enimeronei tin
     * ownTurn() oste na ksekinisoume protoi tis voles.
     *
     * @return
     */
    public boolean ownBattleStations(boolean setOneTime) {
        while (tempCounter != 5) {
            myBoard.setShipBlocks(myShips.getShipBlocks());
            if (myBoard.isShipOnGrid()) {
                myShips.setOnGrid();
                myBoard.setShipOnGrid(false);
                tempCounter++;
            }
            validate();
        }
        return true;
    }
}