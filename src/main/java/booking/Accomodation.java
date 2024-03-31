package booking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.Integer.valueOf;

public class Accomodation implements Serializable {
    private String accType = "";
    private String roomName;
    private Integer numOfPersons;
    private Area area;
    private Integer stars;
    private Integer numOfReviews;
    private Image roomImage;
    private boolean available = false;

    public Accomodation() {
    }
    public Accomodation(JSONObject obj){
        this.accType = obj.getString("accType");
        this.roomName = obj.getString("roomName");
        this.numOfPersons = valueOf(obj.getString("numOfPersons"));
        this.area = new Area(obj);
        this.stars = valueOf(obj.getString("stars"));
        this.numOfReviews = valueOf(obj.getString("numOfReviews"));
        this.roomImage = new Image(obj);
        this.available = obj.getBoolean("available");

    }

    public Accomodation(String roomName, Integer numOfPersons, Area area, Integer stars, Integer numOfReviews, Image roomImage) {
        this.roomName = roomName;
        this.numOfPersons = numOfPersons;
        this.area = area;
        this.stars = stars;
        this.numOfReviews = numOfReviews;
        this.roomImage = roomImage;
    }

    public String getAccType() {
        return accType;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getNumOfPersons() {
        return numOfPersons;
    }

    public Area getArea() {
        return area;
    }

    public Integer getStars() {
        return stars;
    }

    public Integer getNumOfReviews() {
        return numOfReviews;
    }

    public Image getRoomImage() {
        return roomImage;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNumOfPersons(Integer numOfPersons) {
        this.numOfPersons = numOfPersons;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setNumOfReviews(Integer numOfReviews) {
        this.numOfReviews = numOfReviews;
    }

    public void setRoomImage(Image roomImage) {
        this.roomImage = roomImage;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Accomodation{" +
                "roomName='" + roomName + '\'' +
                ", numOfPersons=" + numOfPersons +
                ", area=" + area +
                ", stars=" + stars +
                ", numOfReviews=" + numOfReviews +
                ", roomImage=" + roomImage +
                '}';
    }

    public static void main(String[] args) throws IOException {
        String data = "";
        try{
        data = new String(Files.readAllBytes(Paths.get("src/main/java/booking/accommodations.json")));
        }catch (IOException e){
            e.getStackTrace();
        }
        JSONArray jsonArray = new JSONArray(data);

        JSONObject  jsonObject = jsonArray.getJSONObject(0);
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
        System.out.println();

        System.out.println("City: " + accomodation.getArea().getCity());
        System.out.println("Road: " + accomodation.getArea().getRoad());
        System.out.println("Number: " + accomodation.getArea().getNumber());
        System.out.println("ZipCode: " + accomodation.getArea().getZipCode());
    }
}
