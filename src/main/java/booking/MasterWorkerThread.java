package booking;
import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class MasterWorkerThread extends Thread {
    private Socket socket;
    private Master master;

    public MasterWorkerThread(Socket socket, Master master) {
        this.socket = socket;
        this.master = master;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                try {
                    int index = Integer.parseInt(inputLine);
                    Path jsonFilePath = Path.of("src/main/java/booking/accommodations.json");
                    master.addAccommodation(jsonFilePath, index);
                    out.println("Accommodation added.");
                } catch (NumberFormatException e) {
                    out.println("Error: Invalid index format");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
