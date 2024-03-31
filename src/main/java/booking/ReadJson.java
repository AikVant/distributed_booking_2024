package booking;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sun.management.HotSpotDiagnosticMXBean.ThreadDumpFormat.JSON;

public class ReadJson {
    public static void main(String[] args) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("src/main/java/booking/accommodations.json")));
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject  object = jsonArray.getJSONObject(i);
            String str = jsonArray.getJSONObject(i).toString();
            JSONObject object1 = new JSONObject(str);

            String accType = object1.getString("accType");
            String roomName = object1.getString("roomName");
            String numOfPersons = object1.getString("numOfPersons");
            String area = String.valueOf(object1.getJSONObject("area"));
            String stars = object1.getString("stars");
            String numOfReviews = object1.getString("numOfReviews");
            String roomImage = object1.getString("roomImage");
            String available = object1.getString("available");

            System.out.println(accType + roomName + numOfPersons + area + stars + numOfReviews + roomImage + available);


        }
    }

}
