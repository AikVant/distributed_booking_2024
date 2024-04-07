package booking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static booking.SplitToWorkers.*;

public class Master extends Thread{
    public static List<Accommodation> worker1 = SplitToWorkers.worker1;
    public static List<Accommodation> worker2 = SplitToWorkers.worker2;
    public static List<Accommodation> worker3 = SplitToWorkers.worker3;

    public Master() {

    }

//    private static Object sendSubList1() {
//        return worker1;
//    }
//    private static Object sendSubList2() {
//        return worker2;
//    }
//    private static Object sendSubList3() {
//        return worker3;
//    }

    public void run() {
        Socket requestSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;


        try {
            /* Create socket for contacting the server on port 4321*/
            requestSocket = new Socket("localhost", 3456);

            /* Create the streams to send and receive data from server */
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            in = new ObjectInputStream(requestSocket.getInputStream());


            /* Write the object */
            //out.writeObject(worker);
            out.writeUTF("hello");
            out.flush();
            //SplitToWorkers worker1 = (SplitToWorkers) in.readObject();
//            out.writeObject(sendSubList1());
//            out.flush();
//
//            out.writeObject(sendSubList2());
//            out.flush();
//
//            out.writeObject(sendSubList3());
//            out.flush();

            out.writeObject(worker1);
            out.flush();

            out.writeObject(worker2);
            out.flush();

            out.writeObject(worker3);
            out.flush();

//            System.out.println("Worker: " + sendSubList1().toString());
//            System.out.println("Worker: " + sendSubList2().toString());
//            System.out.println("Worker: " + sendSubList3().toString());

            System.out.println("Worker: " + worker1.toString());
            System.out.println("Worker: " + worker2.toString());
            System.out.println("Worker: " + worker3.toString());

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //oxi auto edw
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SplitToWorkers.setUpWorkersLists();

        System.out.println("-------------------worker1--------------------------------");
        for (Accommodation accommodation : worker1) {
            System.out.println(accommodation);
        }
        System.out.println("-------------------worker2--------------------------------");
        for (Accommodation accommodation : worker2) {
            System.out.println(accommodation);
        }
        System.out.println("-------------------worker3--------------------------------");
        for (Accommodation accommodation : worker3) {
            System.out.println(accommodation);
        }
        new Master().start();
    }
}


