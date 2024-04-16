//package booking;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class ClientHandler extends Thread {
//    ObjectInputStream in;
//    ObjectOutputStream out;
//    public ClientHandler(Socket connection) {
//        try {
//            out = new ObjectOutputStream(connection.getOutputStream());
//            in = new ObjectInputStream(connection.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run() {
//        try {
//            Accommodation accommodation = (Accommodation) in.readObject();
//            out.writeObject(accommodation);
//            out.flush();
//
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                in.close();
//                out.close();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//    }
//}
