import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 10000);
        OutputStream out = s.getOutputStream();
        InputStream in = s.getInputStream();
        Scanner tastiera = new Scanner(System.in);
        Scanner dalServer = new Scanner(in);

        while (true) {
            System.out.print("Stringa: ");
            String stringa = tastiera.nextLine();

            System.out.print("Comando: ");
            String comando = tastiera.nextLine();

            if (comando.equalsIgnoreCase("exit")) {
                out.write("exit\n".getBytes());
                break;
            }

            out.write((stringa + "\n").getBytes());
            out.write((comando + "\n").getBytes());

            String risposta = dalServer.nextLine();

            switch (risposta) {
                case "501":
                    System.out.println(">> Errore 501: la stringa contiene numeri.");
                    break;
                case "502":
                    System.out.println(">> Errore 502: comando inesistente.");
                    break;
                case "505":
                    System.out.println(">> Nota 505: comando normalizzato in maiuscolo.");
                    risposta = dalServer.nextLine();
                    System.out.println(">> " + risposta);
                    break;
                default:
                    System.out.println(">> " + risposta);
            }
        }

        s.close();
        tastiera.close();
    }
}
