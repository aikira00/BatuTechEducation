/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package figlio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author palma
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        //Notare che nel figlio si invertono l'input e l'output rispetto al padre
        System.out.println("ci arriva");
        PrintWriter fileRestituitoDalFiglio=new PrintWriter(new FileOutputStream("C:\\Users\\Sistinformatici PC 4\\testout.dat"));
        BufferedReader  fileInputPerFiglio=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Sistinformatici PC 4\\testin.dat")));
        elaborazione(fileRestituitoDalFiglio,fileInputPerFiglio);
    }

    private static void elaborazione(PrintWriter fileRestituitoDalFiglio, BufferedReader fileInputPerFiglio) throws IOException {
         String linea=null;
         double radice;
         while((linea=fileInputPerFiglio.readLine())!=null){
            radice=Math.sqrt(Integer.parseInt(linea));
            fileRestituitoDalFiglio.println(radice);
         }
         fileInputPerFiglio.close();
         fileRestituitoDalFiglio.close();
    }
}
