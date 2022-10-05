/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nguyenphuduc
 */
public class Server {

    private ServerSocket server = null;

    public Server(int PORT) {
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
        }
    }

    public void action() {
        Socket socket = null;
        int i = 0;
        System.out.println("Server đang lắng nghe...");
        try {
            while ((socket = server.accept()) != null) {
                ServerThread serverThread = new ServerThread(socket, "Client" + i);
                System.out.printf("Thread for Client # %d generating...%n", i++);
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        new Server(8888).action();
    }

}
