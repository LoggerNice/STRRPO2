package Practic_4;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Client {
    private String host;
    private int port;
    private String userName;

    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);
            ExecutorService threadExecutor = Executors.newCachedThreadPool();
            threadExecutor.execute(new ReadThread(socket, this));
            threadExecutor.execute(new WriteThread(socket, this));
            threadExecutor.shutdown();
        } catch (UnknownHostException e) {
            System.out.println("Сервер не запустился " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 4004);
        client.connect();
    }
}