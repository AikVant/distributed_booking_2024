package booking;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    private static final String MASTER_HOST = "localhost"; // or the actual host where the Master is running
    private static final int MASTER_PORT = 5000; // the port Master is listening on

    public static void start() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter filters to send to Master:");
        System.out.println("Choose area:");
        String area = in.nextLine();
        System.out.println("Choose date from (YYYY-MM-DD):");
        String dateFrom = in.nextLine();
        System.out.println("Choose date to (YYYY-MM-DD):");
        String dateTo = in.nextLine();
        System.out.println("How many people:");
        int numOfPersons = in.nextInt();
        System.out.println("Choose minimum price:");
        double minPrice = in.nextDouble();
        System.out.println("Choose maximum price:");
        double maxPrice = in.nextDouble();
        in.nextLine();
        System.out.println("Enter minimum ranking:");
        int minRanking = in.nextInt();
        System.out.println("Enter maximum ranking:");
        int maxRanking = in.nextInt();

        JSONObject filter = new JSONObject();
        filter.put("area", area);
        filter.put("dates", new JSONObject().put("dateFrom", dateFrom).put("dateTo", dateTo));
        filter.put("numOfPersons", numOfPersons);
        filter.put("price", new JSONObject().put("minPrice", minPrice).put("maxPrice", maxPrice));
        filter.put("stars", new JSONObject().put("minRanking", minRanking).put("maxRanking", maxRanking));

        saveFiltersToFile(filter.toString());
        sendToMaster(filter.toString());

        in.close();
    }

    private static void sendToMaster(String jsonFilters) {
        try (Socket socket = new Socket(MASTER_HOST, MASTER_PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            out.writeUTF(jsonFilters);
            out.flush();
            System.out.println("Filters sent to Master.");
        } catch (IOException e) {
            System.err.println("Could not send filters to Master at " + MASTER_HOST + ":" + MASTER_PORT);
            e.printStackTrace();
        }
    }

    private static void saveFiltersToFile(String jsonFilters) {
        try (FileWriter file = new FileWriter("filters.json")) {
            file.write(jsonFilters);
            file.flush();
            System.out.println("Filter saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
