import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class es3EchoMultiServer {
    public static void main(String[] args) throws IOException {

        /*if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }*/

        //int portNumber = Integer.parseInt(args[0]);
        int portNumber = 6000;
        boolean listening = true;
        try (
                ServerSocket serverSocket =
                        new ServerSocket(portNumber)){
                while(listening){
                    new es3EchoServerThread(serverSocket.accept()).start();
                    System.out.println("LOG: accettato nuova connesione, creato thread");
        }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}