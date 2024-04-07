package booking;

import org.json.JSONObject;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Accommodation implements Serializable {
    //private int price;
    private String accType = "";
    private String roomName;
    private Integer numOfPersons;
    private Area area;
    private Integer stars;
    private Integer numOfReviews;
    private Image roomImage;
    private Integer pricePerNight;
    //private boolean available = false;

    public Accommodation() {
    }
   /* public Accommodation(JSONObject obj){
        this.accType = obj.getString("accType");
        this.roomName = obj.getString("roomName");
        this.numOfPersons = valueOf(obj.getString("numOfPersons"));
        JSONObject jsonArea = obj.getJSONObject("area");
        this.area = new Area(jsonArea.getString("city"), jsonArea.getString("road"),
                jsonArea.getString("number"), jsonArea.getString("zipCode"));
        this.stars = valueOf(obj.getString("stars"));
        this.numOfReviews = valueOf(obj.getString("numOfReviews"));
        this.roomImage = new Image(obj);
        this.pricePerNight = Integer.parseInt(obj.getString("pricePerNight"));
    }*/
   public Accommodation(JSONObject obj) {
       this.accType = obj.getString("accType");
       this.roomName = obj.getString("roomName");
       this.numOfPersons = obj.getInt("numOfPersons");
       JSONObject jsonArea = obj.getJSONObject("area");
       this.area = new Area(jsonArea.getString("city"), jsonArea.getString("road"),
               jsonArea.getString("number"), jsonArea.getString("zipCode"));
       this.stars = obj.getInt("stars");
       this.numOfReviews = obj.getInt("numOfReviews");
       this.roomImage = new Image(obj.getString("roomImage"));
       this.pricePerNight = obj.getInt("pricePerNight");
   }


    public Accommodation(String accType, String roomName, Integer numOfPersons, Area area, Integer stars, Integer numOfReviews, Image roomImage, boolean available , int price) {
        this.accType = accType;
        this.roomName = roomName;
        this.numOfPersons = numOfPersons;
        this.area = area;
        this.stars = stars;
        this.numOfReviews = numOfReviews;
        this.roomImage = roomImage;
        this.pricePerNight = price;
    }


    public String getAccType() {
        return accType;
    }

    public Integer getPricePerNight(){return pricePerNight;}

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

    public void setAccType(String accType) {
        this.accType = accType;
    }
    public void setPricePerNight(Integer pricePerNight){
        this.pricePerNight = pricePerNight;
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
    public ReservationDateRange getDateRange() {
        return new ReservationDateRange();
    }
    public int getMaxCapacity() {
        return getNumOfPersons();
    }
    public int getRanking() {
        return getStars();
    }

    /**
     * Reads input from Manager and creates new Accommodation
     * @return Accommodation
     */
    public Accommodation createAccommodation(){
        Scanner in = new Scanner(System.in);
        System.out.println("-----You may add new accommodation-----");

        System.out.println("Add accommodation type: ");
        setAccType(in.nextLine());
        System.out.println("Add roomName: ");
        setRoomName(in.nextLine());
        System.out.println("Add numOfPersons: ");
        setNumOfPersons(valueOf(in.nextLine()));

        Area area = new Area();
        System.out.println("Add city: ");
        area.setCity(in.nextLine());
        System.out.println("Add road: ");
        area.setRoad(in.nextLine());
        System.out.println("Add number: ");
        area.setNumber(in.nextLine());
        System.out.println("Add zipCode: ");
        area.setZipCode(in.nextLine());

        System.out.println("Add roomImage: ");
        setRoomImage(new Image(in.nextLine()));
        System.out.println("Add price per night: ");
        setPricePerNight(valueOf(in.nextLine()));

        return new Accommodation(accType, roomName, numOfPersons, area, 0, 0, roomImage, true, pricePerNight);

    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "accType='" + accType + '\'' +
                ", roomName='" + roomName + '\'' +
                ", numOfPersons=" + numOfPersons +
                ", area=" + area +
                ", stars=" + stars +
                ", numOfReviews=" + numOfReviews +
                ", roomImage=" + roomImage +
                ", pricePerNight=" + pricePerNight +
                '}';
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("accType", this.accType);
        json.put("roomName", this.roomName);
        json.put("numOfPersons",this.numOfPersons);
        json.put("area", new JSONObject()
                .put("city", this.area.getCity())
                .put("road", this.area.getRoad())
                .put("number", this.area.getNumber())
                .put("zipCode", this.area.getZipCode()));
        json.put("stars", this.stars);
        json.put("numOfReviews", this.numOfReviews);
        json.put("roomImage", this.roomImage.getAddress());
        json.put("pricePerNight", this.pricePerNight);
        return json;
    }

    public static void main(String[] args) throws IOException {
        ReadJson.readFile(Path.of("src/main/java/booking/accommodations.json"), 0);
    }
}
