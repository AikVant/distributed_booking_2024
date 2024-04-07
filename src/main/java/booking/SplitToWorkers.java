package booking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SplitToWorkers implements Serializable {

//    static ThreadLocal<Class> Worker1 = new ThreadLocal<Class>();
//    static ThreadLocal<Class> Worker2 = new ThreadLocal<Class>();
//    static ThreadLocal<Class> Worker3 = new ThreadLocal<Class>();
    private static final long serialVersionUID = 1L; // Serialization version

    public static List<Accommodation> worker1 = new ArrayList<>();
    public static List<Accommodation> worker2 = new ArrayList<>();
    public static List<Accommodation> worker3 = new ArrayList<>();

    public static int  sizeOfWorker1(List<Accommodation> list){
        return list.toString().length();
    }

    // Custom serialization method
    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject(); // Serialize non-transient fields

        // Perform custom serialization for other fields if needed

    }

    // Custom deserialization method
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject(); // Deserialize non-transient fields

        // Perform custom deserialization for other fields if needed

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

    public static void main(String[] args) {
        setUpWorkersLists();
        sizeOfWorker1(worker1);
    }
}
