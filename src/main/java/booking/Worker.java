package booking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread{
    ServerSocket worker;
    Socket providerSocket = null;

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

                // Δημιουργία νέου ByteArrayInputStream από τα σειριοποιημένα δεδομένα στη μνήμη
                byte[] serializedDataInMemory1 = new byte[SplitToWorkers.sizeOfWorker1(SplitToWorkers.worker1)];
                ByteArrayInputStream byteStream1 = new ByteArrayInputStream(serializedDataInMemory1);
                in = new ObjectInputStream(byteStream1);
                SplitToWorkers worker1 = (SplitToWorkers) in.readObject();
                in.close();

                byte[] serializedDataInMemory2 = new byte[SplitToWorkers.worker2.size()];
                ByteArrayInputStream byteStream2 = new ByteArrayInputStream(serializedDataInMemory2);
                in = new ObjectInputStream(byteStream2);
                SplitToWorkers worker2 = (SplitToWorkers) in.readObject();
                in.close();

                byte[] serializedDataInMemory3 = new byte[SplitToWorkers.worker3.size()];
                ByteArrayInputStream byteStream3 = new ByteArrayInputStream(serializedDataInMemory1);
                in = new ObjectInputStream(byteStream3);
                SplitToWorkers worker3 = (SplitToWorkers) in.readObject();
                in.close();


                // Επεξεργασία του αντικειμένου
                System.out.println("Deserialized object: " + worker1 + " " + worker2 + " " + worker3);

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
