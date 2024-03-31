package booking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client extends Thread {
    Accomodation accomodation;

    Client(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

    public void run() {
        Socket requestSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;


        try {

            /* Create socket for contacting the server on port 4321*/
            requestSocket = new Socket("localhost", 1234);

            /* Create the streams to send and receive data from server */
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            in = new ObjectInputStream(requestSocket.getInputStream());


            /* Write the object */
            out.writeObject(accomodation);
            Accomodation accomodation1 = (Accomodation) in.readObject();

            System.out.println("Server: " + accomodation1.toString());

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String data = "";
        try{
            data = new String(Files.readAllBytes(Paths.get("src/main/java/booking/accommodations.json")));
        }catch (IOException e){
            e.getStackTrace();
        }
        JSONArray jsonArray = new JSONArray(data);

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String accType = jsonObject.getString("accType");
        String roomName = jsonObject.getString("roomName");
        String numOfPersons = jsonObject.getString("numOfPersons");
        String area = String.valueOf(jsonObject.getJSONObject("area"));
        String stars = jsonObject.getString("stars");
        String numOfReviews = jsonObject.getString("numOfReviews");
        String roomImage = jsonObject.getString("roomImage");
        String available = jsonObject.getString("available");

        System.out.println("accType: " + accType);
        System.out.println("roomName: " + roomName);
        System.out.println("numOfPersons: " + numOfPersons);
        System.out.println("area: " + area);
        System.out.println("stars: " + stars);
        System.out.println("numOfReviews: " + numOfReviews);
        System.out.println("roomImage: " +roomImage);
        System.out.println("available: " + available);

        Accomodation accomodation = new Accomodation(jsonObject);
        new Client(accomodation);
    }
}
