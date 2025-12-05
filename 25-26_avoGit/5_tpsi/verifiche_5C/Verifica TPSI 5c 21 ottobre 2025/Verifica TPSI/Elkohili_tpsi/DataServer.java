import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.format.*;




public class DataServer {
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(6161);
        System.out.println("Server Pronto");
        Socket client = s.accept();
        System.out.println("Client Connesso");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        

        while (true) {
            String richiesta = in.readLine();
            String[] parti = richiesta.split(richiesta, 0);
            String cmd = parti[0];
            double num = Double.parseDouble(parti[1]);
            cmd.toUpperCase();
            String anno = parti[1].substring(4, 8);
            String mese = parti[1].substring(2, 4);

        
            switch (cmd) {
                case "CHECK": 
                    Slash(num);
                    break;
                case "TURNDATE":
                    Contrario(num);
                    break;
                case "LEAP":
                    Leap(anno);
                    break;
                
                case "MONTH":
                    Mese(mese);
                    break;
                    
            }
            
        }

    }

    static void Slash (double numero){
        System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    static void Contrario (double numero){
        System.out.println(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    static void Leap (String anno){
        
        int annonum = Integer.parseInt(anno);
        
        
        
        if(annonum % 4 == 0){
            System.err.println("L'anno e' Bisestile");
        } else{
            System.err.println("L'anno non e' Bisestile");
        }
         
    }

    static void Mese (String mese){
        switch (mese) {
            case "1": 
                System.err.println("Gennaio");
                break;
            case "2":
                System.err.println("Febbraio");
                break;
            case "3":
                System.err.println("Marzo");  
                break;
            
            case "4":
                System.err.println("Aprile"); 
                break;

            case "5":
                System.err.println("Maggio"); 
                break;

            case "6":
                System.err.println("Giugno"); 
                break;

            case "7":
                System.err.println("Luglio"); 
                break;

            case "8":
                System.err.println("Agosto"); 
                break;

            case "9":
                System.err.println("Settembre"); 
                break;

            case "10":
                System.err.println("Ottobre"); 
                break;

            case "11":
                System.err.println("Novembre"); 
                break;

            case "12":
                System.err.println("Dicembre"); 
                break;

            

                
        }
    }
    


}



