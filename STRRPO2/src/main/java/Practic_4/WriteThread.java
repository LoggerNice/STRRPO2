package Practic_4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WriteThread implements Runnable{
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nВведите имя: ");
        String userName = in.nextLine();

        client.setUserName(userName);
        writer.println(userName);

        String text;
        while (true) {
            text = in.nextLine();
            writer.println(text);

        }
    }

}
