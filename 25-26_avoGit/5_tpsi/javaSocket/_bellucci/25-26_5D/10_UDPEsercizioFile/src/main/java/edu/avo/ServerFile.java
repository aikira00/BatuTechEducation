import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class ServerFile {

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket socket=new DatagramSocket(60000);
        byte [] buffer=new byte[1024];
        DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
        socket.receive(packet);
        String message=new String(Arrays.copyOfRange(packet.getData(),0,packet.getLength()));
        buffer="Sono il server".getBytes();
        packet=new DatagramPacket(buffer, buffer.length,
                packet.getAddress(), packet.getPort());
        System.out.println(message);
        socket.send(packet);
    }
}
