/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xu Zhong Xing FILA A
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        String data = "21102025";
        int gg, mm, aaaa;
        int gg2, mm2, aaaa2;
        while (true) {
            try {
                Socket s = ss.accept();
                InputStreamReader mb = new InputStreamReader(s.getInputStream());
                BufferedReader b = new BufferedReader(mb);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                String[] date = b.readLine().split(";");
                gg = Integer.parseInt(date[1].substring(0, 2));
                mm = Integer.parseInt(date[1].substring(2, 4));
                aaaa = Integer.parseInt(date[1].substring(4, 8));
                if (date[1].length() > 8 || mm > 12 || gg > 31) {
                    out.println("ERRORE,");
                } else {
                    String line = date[0];
                    if (line.equals("CHECK")) {
                        if (date[1].equals(data)) {
                            out.println("La data è corretta");
                        } else {
                            out.println("La data è sbagliata");
                        }
                    }

                    if (line.equals("GREATER") ) {
                        gg2 = Integer.parseInt(date[2].substring(0, 2));
                        mm2 = Integer.parseInt(date[2].substring(2, 4));
                        aaaa2 = Integer.parseInt(date[2].substring(4, 8));

                        if (aaaa > aaaa2) {
                            out.println("La prima data è maggiore.");
                        } else {
                            out.println("La second data è maggiore");
                        }
                        if (aaaa == aaaa2 && mm == mm2) {
                            if (gg > gg2) {
                                out.println("La prima data è maggiore.");
                            } else {
                                out.println("La seconda data è maggiore");
                            }

                        } else {
                            if (mm > mm2) {
                                out.println("La prima data è maggiore");
                            } else {
                                out.println("La seconda data è maggiore");
                            }
                        }
                    }
                    if (line.equals("LEAP")) {
                        int aa = Integer.parseInt(date[1].substring(4, 6));
                        if (aaaa % 4 == 0 || aa % 4 == 0) {
                            out.println("L'anno è bisistile");

                        } else {
                            out.println("L'anno non è bisistile");
                        }
                    }
                    if (line.equals("MONTH")) {
                        String m = date[1].substring(2, 4);
                        switch (m) {
                            case "01":
                                out.println("Gennaio");
                                break;
                            case "02":
                                out.println("Febbraio");
                                break;
                            case "03":
                                out.println("Marzo");
                                break;
                            case "04":
                                out.println("Aprile");
                                break;
                            case "05":
                                out.println("Maggio");
                                break;
                            case "06":
                                out.println("Giugno");
                                break;
                            case "07":
                                out.println("Luglio");
                                break;
                            case "08":
                                out.println("Agosto");
                                break;
                            case "10":
                                out.println("Ottobre");
                                break;
                            case "11":
                                out.println("Novembre");
                                break;
                            case "12":
                                out.println("Dicembre");
                                break;
                        }
                    }
                    if (line.equals("SLASH")) {
                        out.println(gg + "/" + mm + "/" + aaaa);
                    }
                    if (line.equals("TURNDATE")) {
                        out.println(aaaa + "" + "" + mm + "" + gg);
                    }

                }
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
