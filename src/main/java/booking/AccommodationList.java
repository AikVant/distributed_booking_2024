package booking;

import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccommodationList {
    static List<Accommodation> accommodationList = new ArrayList<>();
    int length;

    public AccommodationList(){
    }

    /**
     * reads JSON file and fills the array list of accommodations and the map of available rooms
     * @param path
     */
    public AccommodationList(Path path){
        int size = ReadJson.getJsonArray(path).length();
        AvailabilityOfAccommodations availabilityOfAccommodations = new AvailabilityOfAccommodations();
        for (int i = 0; i < size; i++){
            Accommodation accommodation = ReadJson.readFile(Path.of("src/main/java/booking/accommodations.json"), i);
            accommodationList.add(accommodation);
        }
        this.length = size;
    }

    public static List<Accommodation> getAccommodationList() {
        return accommodationList;
    }
    public int getLength(){
            return this.length;
    }
    /**
     * Adds new Accommodation(created from Manager) to accommodationList
     */
    public static void addAccommodation(){
        Accommodation accommodation = new Accommodation();
        Accommodation  newAccommodation = accommodation.createAccommodation();
        accommodationList.add(newAccommodation);
    }
    public static int getLengthOfAccommodationList(){
        return accommodationList.size();
    }
    public Accommodation get(int index){
        return accommodationList.get(index);
    }

    @Override
    public String toString() {
        String s = "";
        for (Accommodation accommodation : accommodationList) {
            s += accommodation + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        AccommodationList list = new AccommodationList(Path.of("src/main/java/booking/accommodations.json"));
        System.out.println(list);
    }
}
