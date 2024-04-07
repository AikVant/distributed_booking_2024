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
    //private boolean available = false;

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
        //this.price = Integer.parseInt(obj.getString("price"));
        //this.available = obj.getBoolean("available");

    }

    public Accommodation(String accType, String roomName, Integer numOfPersons, Area area, Integer stars, Integer numOfReviews, Image roomImage, boolean available , int price) {
        this.accType = accType;
        this.roomName = roomName;
        this.numOfPersons = numOfPersons;
        this.area = area;
        this.stars = stars;
        this.numOfReviews = numOfReviews;
        this.roomImage = roomImage;
        //this.price = price;
        //this.available = available;
    }

    public Accommodation(String accType, String roomName, Integer numOfPersons, Area area, int i, int i1, Image roomImage, boolean b) {
    }


    public String getAccType() {
        return accType;
    }
   // public Integer getPricePerNight(){return price;}

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

//    public boolean isAvailable() {
//        return available;
//    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
    /*public void setPricePerNight(Integer pricePerNight){
        double pricePerNight1 = this.getPricePerNight();
    }*/

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

//    public void setAvailable(boolean available) {
//        this.available = available;
//    }

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
        //System.out.println("Add price per night: ");
        //setPricePerNight(valueOf(in.nextLine()));

        return new Accommodation(accType, roomName, numOfPersons, area, 0, 0, roomImage, true);

    }

    @Override
    public String toString() {
        /*", available=" + available*/
        Object pricePerNight = null;
        return STR."Accommodation{accType='\{accType}\{'\''}, roomName='\{roomName}\{'\''}, numOfPersons=\{numOfPersons}, area=\{area}, stars=\{stars}, numOfReviews=\{numOfReviews}, roomImage=\{roomImage},,\{+
                '}'}" ;
    }
    //pricePerNight=\{pricePerNight}?????

    public static void main(String[] args) throws IOException {
        ReadJson.readFile(Path.of("src/main/java/booking/accommodations.json"), 0);
    }

    public ReservationDateRange getDateRange() {
        return new ReservationDateRange();
    }

    public int getMaxCapacity() {
        return getNumOfPersons();
    }

    /*public double getPricePerNight() {
        return getPricePerNight();
    }*/

    public int getRanking() {
        return getStars();
    }

    /*public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }*/
}
