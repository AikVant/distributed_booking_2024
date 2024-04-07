package booking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread{
    ServerSocket worker;
    Socket providerSocket = null;
    public List<Accommodation> worker1 = new ArrayList<>();
    public List<Accommodation> worker2 = new ArrayList<>();
    public List<Accommodation> worker3 = new ArrayList<>();


    public void run(){
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try{
            /* Create Worker Socket */
            worker = new ServerSocket(3456);

            while (true){
                /* Accept the connection */
                providerSocket = worker.accept();
                in = new ObjectInputStream(providerSocket.getInputStream());
                out = new ObjectOutputStream(providerSocket.getOutputStream());

                System.out.println(in.readUTF()); // in from Master - hello

                // Ta diavazei alla giati den mporei na ta ekxwrisei???
                System.out.println(in.readObject().toString());
                System.out.println(in.readObject().toString());
                System.out.println(in.readObject().toString());

                SplitToWorkers worker1 = (SplitToWorkers) in.readObject();
                SplitToWorkers worker2 = (SplitToWorkers) in.readObject();
                SplitToWorkers worker3 = (SplitToWorkers) in.readObject();

                /* Handle the request */
                Thread t = new WorkerHandler(providerSocket);
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try{
                providerSocket.close();
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Worker accepts the connection and waits...");
        new Worker().start();
        System.out.println("lala");

    }
}
