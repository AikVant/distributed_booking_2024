package booking;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Master {
    private static final int CLIENT_PORT = 12345;
    private static final int WORKER_PORT =  12349;


    public static List<Accommodation> worker1 = new ArrayList<>();
    public static List<Accommodation> worker2 = new ArrayList<>();
    public static List<Accommodation> worker3 = new ArrayList<>();

    public static void main(String[] args) {
        AccommodationList accommodationList = new AccommodationList(Path.of("src/main/java/booking/accommodations.json"));
        int index = 0;
        for (int i = 0; i < accommodationList.getLength(); i++){
            index = i % 3;
            if (index == 0){
                worker1.add(accommodationList.get(i));
            }
            if (index == 1){
                worker2.add(accommodationList.get(i));
            }
            if (index == 2){
                worker3.add(accommodationList.get(i));
            }
        }
        System.out.println("-------------------worker1--------------------------------");
        for (int i = 0; i < worker1.size(); i++){
            System.out.println(worker1.get(i));
        }
        System.out.println("-------------------worker2--------------------------------");
        for (int i = 0; i < worker2.size(); i++){
            System.out.println(worker2.get(i));
        }
        System.out.println("-------------------worker3--------------------------------");
        for (int i = 0; i < worker3.size(); i++){
            System.out.println(worker3.get(i));
        }


    }
}
