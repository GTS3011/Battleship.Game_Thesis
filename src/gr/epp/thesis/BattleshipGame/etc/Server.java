/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.epp.thesis.BattleshipGame.etc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author USER1
 */
public class Server {

    private static ServerSocket serverSocket = null;
    private static ServerSocket serverSocket2 = null;
    private static Socket clientSocket = null;
    private static Socket clientSocket2 = null;
    private static final int maxClientsCount = 10; //Maximum number of connections supported by the server.
    private static final ClientThread[] threads = new ClientThread[maxClientsCount];
    private static final NumbersThread[] threads2 = new NumbersThread[maxClientsCount];

    public static void main(String[] args) {
        int portNumber = 1501;
        int portNumber2 = 1502;

        if (args.length < 1) {
            System.out.println("Now using port number=" + portNumber);
            System.out.println("Now using port number=" + portNumber2);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }

        try {
            //Opens a server socket in ports 1501 and 1502(portNumber).
            serverSocket = new ServerSocket(portNumber);
            serverSocket2 = new ServerSocket(portNumber2);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                clientSocket2 = serverSocket2.accept();
                System.out.println("User Connected in port: " + portNumber);
                System.out.println("User Connected in port: " + portNumber2);
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null && threads2[i] == null) {
                        (threads[i] = new ClientThread(clientSocket, threads)).start();
                        (threads2[i] = new NumbersThread(clientSocket2, threads2)).start();
                        break;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}