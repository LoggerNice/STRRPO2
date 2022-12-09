package Practic_4;

import java.io.*;
import java.net.*;

public class ReadThread implements Runnable {
    private BufferedReader reader;
    private Socket socket;
    private Client client;

    public ReadThread(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    public void run(){
        while(true){
            try {
                String response = reader.readLine();
                System.out.print(response + "\n");
            } catch (IOException ex) {
                break;
            }
        }
    }
}
