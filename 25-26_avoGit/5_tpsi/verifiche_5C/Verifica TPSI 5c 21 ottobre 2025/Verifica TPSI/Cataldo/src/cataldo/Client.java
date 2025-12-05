package cataldo_verifica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
    
    private static String interpretaRisposta(String risposta){
            if(risposta == null || risposta.isEmpty())
                return "Nessuna risposta dal server";
            
            if(risposta.startsWith("ERR;")){
                switch(risposta){
                    case "ERR;02":
                        return "Comando inesistente";
                    case "ERR;01":
                        return "Numero argomenti sbagliato";
                    case "ERR;03":
                        return "Lettera non presente";
                }
            }
            
            if(risposta.startsWith("OK;COUNT")){
                return "count lettere: " + risposta.substring(9);
            } else if(risposta.startsWith("OK;TRUE")){
                return "numeri vocali maggiore";
            } else if(risposta.startsWith("OK;FALSE")){
                return "numero vocali minore o uguale";
            } else if(risposta.startsWith("OK;INVBIN")){
                return "Inversione pari con dispari: " + risposta.substring(10);
            } else if(risposta.startsWith("OK;REVERSE")){
                return "Stringa invertita: " + risposta.substring(11);
            } else if(risposta.startsWith("OK;VOWEL")){
                return "Numero vocali: " + risposta.substring(9);
            } else if(risposta.startsWith("OK;CHANGE")){
                return "Stringa dopo la sostituzione: " + risposta.substring(10);
            }
            
            return null;
        }
    
    public static void main(String[] args){
       
        JFrame frame = new JFrame("Elaborazione Stringa");
        frame.setSize(600, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton esegui = new JButton("ESEGUI");
        frame.add(esegui);
        esegui.setBounds(20, 90, 100, 50);
        
        JLabel label1 = new JLabel("Inserisci il comando");
        frame.add(label1);
        label1.setBounds(20, 0, 150, 100);
        
        JTextField jtxt1 = new JTextField();
        frame.add(jtxt1);
        jtxt1.setBounds(150, 45, 150, 20);
        
        JLabel label3 = new JLabel("risultato: ");
        frame.add(label3);
        label3.setBounds(150, 40, 150, 100);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(textArea);
        textArea.setBounds(220, 80, 250, 20);
        
        esegui.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket s;
                try {
                    s = new Socket("localhost", 9999);
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    
                    String input = jtxt1.getText().trim();
                    out.println(input);
                    
                    String risposta = in.readLine();
                    
                    if(risposta != null){
                        if(risposta.startsWith("ERR;")){
                            textArea.setText(interpretaRisposta(risposta));
                            textArea.setBackground(Color.red);
                        }
                        else if(risposta.startsWith("OK")){
                            textArea.setText(interpretaRisposta(risposta));
                            textArea.setBackground(Color.green);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
        });
    }
}
