package booking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJson {
    public static Accommodation readFile(Path path, int index){
        String data = "";
        try{
            //data = new String(Files.readAllBytes(Paths.get("src/main/java/booking/accommodations.json")));
            data = new String(Files.readAllBytes(Paths.get(path.toUri())));
        }catch (IOException e){
            e.getStackTrace();
        }
        JSONArray jsonArray = new JSONArray(data);

        JSONObject jsonObject = jsonArray.getJSONObject(index);
        String accType = jsonObject.getString("accType");
        String roomName = jsonObject.getString("roomName");
        String numOfPersons = jsonObject.getString("numOfPersons");
        String area = String.valueOf(jsonObject.getJSONObject("area"));
        String stars = jsonObject.getString("stars");
        String numOfReviews = jsonObject.getString("numOfReviews");
        String roomImage = jsonObject.getString("roomImage");
        String available = jsonObject.getString("available");

//        System.out.println("accType: " + accType);
//        System.out.println("roomName: " + roomName);
//        System.out.println("numOfPersons: " + numOfPersons);
//        System.out.println("area: " + area);
//        System.out.println("stars: " + stars);
//        System.out.println("numOfReviews: " + numOfReviews);
//        System.out.println("roomImage: " +roomImage);
//        System.out.println("available: " + available);

        Accommodation accommodation = new Accommodation(jsonObject);
//        System.out.println();
//
//        System.out.println("City: " + accommodation.getArea().getCity());
//        System.out.println("Road: " + accommodation.getArea().getRoad());
//        System.out.println("Number: " + accommodation.getArea().getNumber());
//        System.out.println("ZipCode: " + accommodation.getArea().getZipCode());
//        System.out.println();

        return accommodation;
    }
}


