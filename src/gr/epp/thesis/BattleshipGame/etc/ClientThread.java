package gr.epp.thesis.BattleshipGame.etc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author George Tsoutsas, 2542
 */
/**
 * Notifies users about the number of clients that are connected to the server.
 */
public class ClientThread extends Thread {

    public BufferedReader in = null;
    public DataOutputStream out = null;
    private Socket clientSocket;
    private final ClientThread[] threads;
    private int maxClientsCount;
    private int clients = 0;

    public ClientThread(Socket clientSocket, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    /**
     * A thread that calculates and notifies the users about the number of
     * clients connected with the server.
     */
    @Override
    public void run() {
        try {
            in = new BufferedReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            out = new DataOutputStream(clientSocket.getOutputStream());


            synchronized (this) {
                // Counts the clients
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null) {
                        clients++;
                    }
                }
                // Notifies Clients about the number of clients.
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null) {
                        System.out.println("To PC " + i + ", notification: " + clients + " users");
                        threads[i].out.writeInt(clients);
                    }
                }
            }
            in.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            // When a client disconnects, the server empties its position and informs the other clients.
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null) {
                        try {
                            if (threads[i] != this) {
                                threads[i].out.writeInt(0);
                            } else {
                                threads[i] = null;
                            }
                        } catch (IOException ex1) {
                            System.out.println(ex1.getMessage());
                        }
                    }
                }
            }
        }
    }
}
