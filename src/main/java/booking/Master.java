package booking;
import booking.Accommodation;
import booking.ReadJson;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static booking.ReadJson.getJsonArray;

public class Master {
    private static final int PORT = 5000;
    private List<String> workerNodes;
    private List<Accommodation> accommodations;

    public Master(List<String> workerNodes) {
        this.workerNodes = new ArrayList<>(workerNodes);
        accommodations = new ArrayList<>();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Master Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (isFilterWorkerThread(clientSocket)){
                    new FilterWorkerThread(clientSocket, this).start();
                }else{
                    new MasterWorkerThread(clientSocket, this).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFilterWorkerThread(Socket socket){
        return false;
    }

    public synchronized void filterAccommodations(JSONObject filters) {
        String area = filters.getString("area");
        JSONObject dates = filters.getJSONObject("dates");
        String dateFrom = dates.getString("dateFrom");
        String dateTo = dates.getString("dateTo");
        LocalDate fromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ISO_LOCAL_DATE);
        int numOfPersons = filters.getInt("numOfPersons");
        JSONObject price = filters.getJSONObject("price");
        int minPrice = price.getInt("min");
        int maxPrice = price.getInt("max");
        JSONObject stars = filters.getJSONObject("stars");
        int minRanking = price.getInt("min");
        int maxRanking = price.getInt("max");

        List<AccommodationFilter> filteredAccommodations = accommodations.stream()
                .filter(acc -> acc.getArea().getCity().equalsIgnoreCase(area))
                .filter(acc -> acc.getNumOfPersons().equals(numOfPersons))
                .filter(acc -> acc.getPricePerNight() >= minPrice && acc.getPricePerNight() <= maxPrice)
                .filter(acc -> acc.getStars().equals(stars))
                .map(acc -> new AccommodationFilter(acc.getAccType(), acc.getRoomName(), acc.getNumOfPersons(),
                        acc.getArea(), acc.getStars(), acc.getNumOfReviews(),
                        acc.getRoomImage(), acc.getPricePerNight(),
                        minPrice, maxPrice, minRanking, maxRanking, fromDate, toDate))
                .collect(Collectors.toList());
        //Na ginei:
        //distributeFilteredAccommodations(filteredAccommodations);
    }

    private void distributeFilters(List<AccommodationFilter> filters){

    }
    public void addAccommodationFromJson(JSONObject jsonAccommodation){
        Accommodation accommodation = new Accommodation(jsonAccommodation);
        accommodations.add(accommodation);
    }

    public synchronized void addAccommodation(Accommodation accommodation){
        accommodations.add(accommodation);
        System.out.println(accommodations);
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
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: No worker nodes specified");
            System.exit(1);
        }

        Master master = new Master(Arrays.asList(args));
        master.startServer();
    }
}
