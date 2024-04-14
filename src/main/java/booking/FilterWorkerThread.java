package booking;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;

public class FilterWorkerThread extends Thread {
    private Socket socket;
    private Master master; // Ensure Master class can process JSON accommodations

    public FilterWorkerThread(Socket socket, Master master) {
        this.socket = socket;
        this.master = master;
    }

    private void processFilter(String jsonFilter) {
        try {
            JSONObject filters = new JSONObject(jsonFilter); // Properly parse the incoming JSON string
            master.filterAccommodations(filters); // Use the parsed JSON object
        } catch (JSONException e) {
            System.err.println("JSON parsing error: " + e.getMessage());
        }
    }


    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            String jsonFilter = dataInputStream.readUTF();
            processFilter(jsonFilter);
        } catch (Exception e) {
            System.out.println("Error in filter input");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}