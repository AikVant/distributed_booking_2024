package booking2;
import booking.Accommodation;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class Worker implements Runnable{
    private ServerSocket serverSocket;
    private ConcurrentHashMap<String, Accommodation> accommodations;

    public Worker(int port) {
        accommodations = new ConcurrentHashMap<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Worker running on port " + serverSocket.getLocalPort());
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        // neo thread gia kathe client
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Received from Master: " + input);
                    //EDW TODO: parse input, add/update accommodations
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Port Number ");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        new Thread(new Worker(port)).start();
    }
}
