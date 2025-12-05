import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(10000);
        System.out.println("Server in ascolto sulla porta 10000");
        Socket client = server.accept();
        System.out.println("Client connesso.");

        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        Scanner sc = new Scanner(in);

        while (true) {

            String stringa = sc.nextLine();
            String comandoOriginale = sc.nextLine();
            String comando = comandoOriginale.toUpperCase();

            if (comando.equals("EXIT") || comando.equals("exit")) {
                break;
            }

            boolean comandoValido = comando.equals("COUNT") ||
                    comando.equals("CHANGE") ||
                    comando.equals("CHECK") ||
                    comando.equals("INVBIN") ||
                    comando.equals("REVERSE") ||
                    comando.equals("VOWEL");

            if (!comandoValido) {
                out.write("502\n".getBytes());
                continue;
            }

            //maiuscolo 505
            if (!comando.equals(comandoOriginale)) {
                out.write("505\n".getBytes());
            }

            String r = "";

            switch (comando) {
                case "COUNT":
                    r = "" + stringa.length();
                    break;
                case "CHANGE":
                    r = stringa.replace('a', 'b');
                    break;
                case "CHECK":
                    r = "" + (vocali(stringa) > consonanti(stringa));
                    break;
                case "INVBIN":
                    r = invbin(stringa);
                    break;
                case "REVERSE":

                    r = "da implementare";

                  /*  r = "";
                    Array a = stringa.toCharArray();

                    for (int i = stringa.length() - 1; i >= 0; i--) {
                        r = r + a[i];
                    */
                    break;
                case "VOWEL":
                    r = "" + vocali(stringa);
                    break;
            }

            out.write((r + "\n").getBytes());
        }

        client.close();
        server.close();
        System.out.println("Server chiuso.");
    }

    static int vocali(String s) {
        int v = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) v++;
        }
        return v;
    }

    static int consonanti(String s) {
        int c = 0;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch) && "aeiou".indexOf(ch) == -1) c++;
        }
        return c;
    }

    static String invbin(String s) {
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length - 1; i += 2) {
            char tmp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = tmp;
        }
        return new String(a);
    }
}
