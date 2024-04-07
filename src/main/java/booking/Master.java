package booking;
import booking.Accommodation;
import booking.ReadJson;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Master {
    private static final int PORT = 5000;
    private List<String> workerNodes;
    private List<Accommodation> accommodations;

    public Master() {
        workerNodes = new ArrayList<>();
        accommodations = new ArrayList<>();

        // Ip gia workers placeholder pros to parwn
        workerNodes.add("127.0.0.1:5001"); // Worker 1
        workerNodes.add("127.0.0.1:5002"); // Worker 2
        workerNodes.add("127.0.0.1:5003"); // Worker 3
    }

    public static void main(String[] args) {
        Master master = new Master();
        master.startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Master Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new MasterWorkerThread(clientSocket, this).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addAccommodation(Path jsonFilePath, int index) {
        Accommodation accommodation = ReadJson.readFile(jsonFilePath, index);
        accommodations.add(accommodation);

        //Stelnei ean accommodation sto swsto worker analoga me to onoma tou dwmatiou
        String workerInfo = getWorkerNode(accommodation.getRoomName());
        sendAccommodationToWorker(workerInfo, accommodation);
    }

    private String getWorkerNode(String roomName) {
        int nodeIndex = Math.abs(roomName.hashCode()) % workerNodes.size();
        return workerNodes.get(nodeIndex);
    }

    private void sendAccommodationToWorker(String workerInfo, Accommodation accommodation) {
        String[] parts = workerInfo.split(":");
        String workerHost = parts[0];
        int workerPort = Integer.parseInt(parts[1]);

        try (Socket workerSocket = new Socket(workerHost, workerPort);
             PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true)) {

            JSONObject jsonAccommodation = new JSONObject();
            jsonAccommodation.put("accType", accommodation.getAccType());
            jsonAccommodation.put("roomName", accommodation.getRoomName());
            jsonAccommodation.put("numOfPersons", accommodation.getNumOfPersons().toString());

            JSONObject jsonArea = new JSONObject();
            jsonArea.put("city", accommodation.getArea().getCity());
            jsonArea.put("road", accommodation.getArea().getRoad());
            jsonArea.put("number", accommodation.getArea().getNumber());
            jsonArea.put("zipCode", accommodation.getArea().getZipCode());
            jsonAccommodation.put("area", jsonArea);

            jsonAccommodation.put("stars", accommodation.getStars().toString());
            jsonAccommodation.put("numOfReviews", accommodation.getNumOfReviews().toString());
            jsonAccommodation.put("roomImage", accommodation.getRoomImage().getAddress());
            jsonAccommodation.put("pricePerNight", accommodation.getPricePerNight().toString());
            out.println(jsonAccommodation.toString());
            System.out.println("Sent " + accommodation.getRoomName() + " to worker at " + workerInfo);
        } catch (IOException e) {
            System.err.println("Could not send accommodation to worker " + workerInfo);
            e.printStackTrace();
        }
    }
}
