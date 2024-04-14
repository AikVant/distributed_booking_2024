package booking;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Worker implements Runnable {
    private ServerSocket serverSocket;
    private final AccommodationList accommodations;

    public Worker(int port) {
        accommodations = new AccommodationList();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public AccommodationList getAccommodations() {
        return accommodations;
    }
    public Accommodation getAccommodations(int index) {
        return accommodations.get(index);
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
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from Master: " + inputLine);
                    try {

                        JSONObject jsonAccommodation = new JSONObject(inputLine);
                        Accommodation accommodation = new Accommodation(jsonAccommodation);
                        accommodations.addAccommodation(accommodation);

                        System.out.println("Accommodation added: " + accommodation.getRoomName());
                    } catch (Exception e) {
                        System.err.println("Error parsing accommodation JSON: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException in handleClient: " + e.getMessage());
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