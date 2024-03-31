package booking;

import org.json.JSONObject;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import static java.lang.Integer.valueOf;

public class Accommodation implements Serializable {
    private String accType = "";
    private String roomName;
    private Integer numOfPersons;
    private Area area;
    private Integer stars;
    private Integer numOfReviews;
    private Image roomImage;
    private boolean available = false;

    public Accommodation() {
    }
    public Accommodation(JSONObject obj){
        this.accType = obj.getString("accType");
        this.roomName = obj.getString("roomName");
        this.numOfPersons = valueOf(obj.getString("numOfPersons"));
        JSONObject jsonArea = obj.getJSONObject("area");
        this.area = new Area(jsonArea.getString("city"), jsonArea.getString("road"),
                jsonArea.getString("number"), jsonArea.getString("zipCode"));
        this.stars = valueOf(obj.getString("stars"));
        this.numOfReviews = valueOf(obj.getString("numOfReviews"));
        this.roomImage = new Image(obj);
        this.available = obj.getBoolean("available");

    }

    public Accommodation(String roomName, Integer numOfPersons, Area area, Integer stars, Integer numOfReviews, Image roomImage) {
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
        ReadJson.readFile(Path.of("src/main/java/booking/accommodations.json"), 0);
    }
}
