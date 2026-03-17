/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package padre;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        PrintWriter fileInputPerFiglio=new PrintWriter(new FileOutputStream("C:\\Users\\Sistinformatici PC 4\\testin.dat"));
        scritturaDatiInInputPerIlFiglio(fileInputPerFiglio);
        fileInputPerFiglio.close();
        //i tre parametri sono 1 percorso del file java.exe 2 tipo di file se class o jar 3 prcorso del file jar del figlio
        String []parametriPeresecuzioneFiglio={"java", "-jar", "C:\\Users\\Sistinformatici PC 4\\Downloads\\ProcessiJava\\ProcessiJava\\Figlio\\dist\\Figlio.jar"};
        Process processoFiglio=new ProcessBuilder( parametriPeresecuzioneFiglio).start();
        processoFiglio.waitFor();
        int valoreRestituitodalFiglio=processoFiglio.exitValue();
        if (valoreRestituitodalFiglio==0){
            BufferedReader fileRestituitoDalFiglio=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Sistinformatici PC 4\\testout.dat")));
            elaborazioneDatiRestituitiDalFiglio(fileRestituitoDalFiglio);
        }else{
            System.out.println("Figlio terminato male");
        }
    }

    private static void scritturaDatiInInputPerIlFiglio(PrintWriter fileInputPerFiglio) {
       //Qui, secondo l'applicazione che si sta sviluppando si scrivono i dati nel file per il figlio
       //Nell'esempio scriverò 3 numeri interi inseriti dall'utente
       int numero;
       for(int i=0;i<3;i++){
           numero=Integer.parseInt(JOptionPane.showInputDialog("Inserire un numero intero"));
           fileInputPerFiglio.println(numero);
       }
       fileInputPerFiglio.close();
    }

    private static void elaborazioneDatiRestituitiDalFiglio(BufferedReader fileRestituitoDalFiglio) throws IOException {
        //Qui leggo i dati che il figlio ha prodotto. Nell' esempio il figlio calcola la radice quadrata di ognuno dei numeri inseriti
        //Nel padre stampo questi valori. Notare che sapendo che son tre uso il for. Se non lo sapessi,
        //come  nel figlio,  userei il while
         for(int i=0;i<3;i++){
           System.out.println(fileRestituitoDalFiglio.readLine());
       }
       fileRestituitoDalFiglio.close();
    }

}
