package booking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /* Define the socket that receives requests */
    ServerSocket server;
    Socket providerSocket = null;

    /* Define the socket that is used to handle the connection */

    void openServer() {
        try {

            /* Create Server Socket */
            server = new ServerSocket(1234);


            while (true) {
                /* Accept the connection */
                providerSocket = server.accept();


                /* Handle the request */
                Thread t = new ClientHandler(providerSocket);
                t.start();

            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().openServer();
    }
}
