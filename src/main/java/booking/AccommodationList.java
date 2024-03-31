package booking;

import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccommodationList {
    List<Accommodation> accommodationList = new ArrayList<>();

    public AccommodationList(){
        for (int i = 0; i < 2; i++){
            Accommodation accommodation = ReadJson.readFile(Path.of("src/main/java/booking/accommodations.json"), i);
            accommodationList.add(accommodation);
        }
    }

    public List<Accommodation> getAccommodationList() {
        return accommodationList;
    }
    public int getLengthOfAccommodationList(){
        return accommodationList.size();
    }
    public Accommodation get(int index){
        return accommodationList.get(index);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < accommodationList.size(); i++){
            s += accommodationList.get(i) + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        AccommodationList list = new AccommodationList();
        System.out.println(list);
    }
}
