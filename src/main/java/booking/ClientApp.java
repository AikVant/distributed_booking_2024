package booking;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    private static final String MASTER_HOST = "localhost"; // or the actual host where the Master is running
    private static final int MASTER_PORT = 5000; // the port Master is listening on

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

    public static void main(String[] args) {
        JSONObject filter = new JSONObject();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter filters");
        System.out.println("Choose area");
        String area = in.nextLine();
        System.out.println("Choose date from");
        String dateFrom = in.nextLine();
        System.out.println("Choose date to");
        String dateTo = in.nextLine();
        System.out.println("How many people");
        String numOfPeople = in.nextLine();
        System.out.println("Choose minimum price");
        String minPrice = in.nextLine();
        System.out.println("Choose maximum price");
        String maxPrice = in.nextLine();
        System.out.println("Enter minimum ranking");
        String minRanking = in.nextLine();
        System.out.println("Enter maximum ranking");
        String maxRanking = in.nextLine();


        filter.put("area", area);
        filter.put("dates", new JSONObject().put("dateFrom", dateFrom).put("dateTo", dateTo));
        filter.put("numOfPeople", numOfPeople);
        filter.put("price", new JSONObject().put("minPrice", minPrice).put("maxPrice", maxPrice));
        filter.put("ranking", new JSONObject().put("minRanking", minRanking).put("maxRanking", maxRanking));

        try(FileWriter file = new FileWriter("filters.json")){
            file.write(filter.toString());
            file.flush();
            System.out.println("filter saved to json");
        }catch (Exception e){
            e.printStackTrace();
        }

        String filtersString = filter.toString();
        sendToMaster(filtersString);

    }
}
