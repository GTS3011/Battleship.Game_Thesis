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
public class Player extends Thread {

    private static Socket clientSocket = null;
    private static Socket clientSocket2 = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    Battleship_Game start;

    public Player() {
        
    }

    @Override
    public void run() {
        start = new Battleship_Game();
    }

    public static void main(String[] args) {
        //Starts the thread to listen for client number
        Thread t = new Thread(new Player());
        Thread t2 = new Thread(new Player());
        t.start();
        t2.start();
        //Battleship_Game entryPoint = new Battleship_Game();
    }
}
