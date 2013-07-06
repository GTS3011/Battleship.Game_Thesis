/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.epp.thesis.BattleshipGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author USER1
 */
public class Player implements Runnable {

    private static Socket clientSocket = null;
    private static Socket clientSocket2 = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        Battleship_Game entryPoint = new Battleship_Game();
    }
}
