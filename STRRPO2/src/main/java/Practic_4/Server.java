package Practic_4;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Server {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            ExecutorService threadExecutor = Executors.newCachedThreadPool();

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                threadExecutor.execute(newUser);
            }
        } catch (IOException e){
            System.out.println("Ошибка при создании сервера \n" + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Server server = new Server(4004);
        server.startServer();
    }

    void broadcast(String message, UserThread excludeUser) {
        for (UserThread eachUser : userThreads)
            if (eachUser != excludeUser) eachUser.sendMessage(message);
    }

    void addUserName(String userName) {
        userNames.add(userName);
    }
}