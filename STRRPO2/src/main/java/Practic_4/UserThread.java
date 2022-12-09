package Practic_4;

import java.io.*;
import java.net.*;

public class UserThread implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            String userName = reader.readLine();
            server.addUserName(userName);
            System.out.println(userName + " подключился");

            String clientMessage;
            while (true) {
                clientMessage = reader.readLine();
                server.broadcast(userName + ": " + clientMessage, this);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }
}