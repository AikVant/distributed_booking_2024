package booking;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SplitToWorkers implements Serializable {

//    static ThreadLocal<Class> Worker1 = new ThreadLocal<Class>();
//    static ThreadLocal<Class> Worker2 = new ThreadLocal<Class>();
//    static ThreadLocal<Class> Worker3 = new ThreadLocal<Class>();

    public static List<Accommodation> worker1 = new ArrayList<>();
    public static List<Accommodation> worker2 = new ArrayList<>();
    public static List<Accommodation> worker3 = new ArrayList<>();

    public static int getLengthWorker(List<Accommodation> list){
        return list.size();
    }

    public static void setUpWorkersLists(){
        AccommodationList accommodationList = new AccommodationList(Path.of("src/main/java/booking/accommodations.json"));
        int index = 0;
        for (int i = 0; i < accommodationList.getLength(); i++){
            index = i % 3;
            if (index == 0){
                //Worker1.get().cast(accommodationList.get(i));
                worker1.add(accommodationList.get(i));
            }
            if (index == 1){
                //Worker2.get().cast(accommodationList.get(i));
                worker2.add(accommodationList.get(i));
            }
            if (index == 2){
                //Worker3.get().cast(accommodationList.get(i));
                worker3.add(accommodationList.get(i));
            }
        }
    }
}
