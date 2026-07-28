import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class es3EchoServerThread extends Thread {

    private Socket socket = null;

    public es3EchoServerThread(Socket clientSocket){
        socket = clientSocket;
    }

    public void run(){
        try ( PrintWriter out =
                      new PrintWriter(socket.getOutputStream(), true);
              BufferedReader in = new BufferedReader(
                      new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Ricevo messaggio da client " + socket.getPort());
                out.println(inputLine);
            }
            System.out.println("Client interrotto comunicazione " + socket.getPort());
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + socket.getLocalPort() + " or listening for a connection");
            System.out.println(e.getMessage());
        }
        System.out.println("FINE CONNESSIONE  " + socket.getPort() + " " + socket.getLocalPort());

    }
}
