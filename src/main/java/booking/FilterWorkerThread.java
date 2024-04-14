package booking;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilterWorkerThread extends Thread {
    private Socket socket;
    private Master master; // Ensure Master class can process JSON accommodations

    public FilterWorkerThread(Socket socket, Master master) {
        this.socket = socket;
        this.master = master;
    }

    private void processFilter(String jsonFilter) {
        JSONObject filters = new JSONObject();
        new JSONObject(jsonFilter);
        master.filterAccommodations(filters);
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
