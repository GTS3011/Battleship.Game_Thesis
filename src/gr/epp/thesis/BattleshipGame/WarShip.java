package gr.epp.thesis.BattleshipGame;

import java.awt.Color;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 */
public class WarShip extends JButton {

    private int warshipBlocks;
    private Border border = new LineBorder(Color.GREEN, 1, false);
    private HashSet<Integer> coordinates;

    public WarShip(int warshipBlocks, Color color) {
        this.warshipBlocks = warshipBlocks;
        setBackground(color);
        setBorder(border);
        coordinates = new HashSet<Integer>(warshipBlocks);
    }

    //Getters & Setters :
    public int getWarshipBlocks() {
        return warshipBlocks;
    }

    public void setWarshipBlocks(int warshipBlocks) {
        this.warshipBlocks = warshipBlocks;
    }

    public HashSet<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(HashSet<Integer> coordinates) {
        this.coordinates = coordinates;
    }
    
    public void addCoordinate(int coordinate){
        coordinates.add(coordinate);
    }
}