/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stringex;

import java.awt.Font;
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

/**
 *
 * @author RAMLI ADAM FILA B
 */
public class StringClient extends JFrame implements ActionListener, Runnable {

    private JLabel titolo_label = new JLabel("Test sulle stringhe");
    private JButton username_button = new JButton("Conferma nome");
    private JButton string_button = new JButton("Invia stringa");
    private JTextField string_field = new JTextField();
    private JTextField username_field = new JTextField();
    private JTextArea output_area = new JTextArea();
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(270, 500);
        this.setResizable(false);
        titolo_label.setFont(new Font("Myriad Pro", Font.BOLD, 15));
        titolo_label.setBounds(60, 10, 150, 40);
        string_button.setBounds(120, 50, 130, 40);
        username_button.setBounds(120, 100, 130, 40);
        username_field.setBounds(10, 100, 100, 40);
        string_field.setBounds(10, 50, 100, 40);
        output_area.setBounds(10, 170, 235, 270);
        this.add(username_button);
        this.add(string_button);
        this.add(string_field);
        this.add(username_field);
        this.add(titolo_label);
        this.add(output_area);
        string_button.addActionListener(this);
        username_button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == username_button) {
            try {
                s = new Socket("localhost", 16384);
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out.println(username_field.getText());
                output_area.append("Ti sei connesso come: " + username_field.getText() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(StringClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == string_button) {
            try {
                String[] std_messages = {"COUNT", "CHANGE", "CHECK", "INVBIN", "REVERSE", "VOWEL", "ERROR", "EXIT"};
                out.println(string_field.getText());
                String[] message = in.readLine().split(";");
                switch (message[0]) {
                    case "COUNT":
                        output_area.append("Il numero di lettere è: " + message[1] + "\n");
                        break;
                    case "CHANGE":
                        output_area.append("Stringa sostituita: " + message[1] + "\n");
                        break;
                    case "CHECK":
                        if(message[1].equals("VOCALI"))
                            output_area.append("Ci sono più vocali che consonanti\n" );
                        else if(message[1].equals("CONSONANTI"))
                            output_area.append("Ci sono più consonanti che vocali\n");
                        break;
                    case "INVBIN":
                        output_area.append("Ecco la stringa invertita: " + message[1] + "\n");
                        break;
                    case "REVERSE":
                        output_area.append("Ecco la stringa(REVERSE) invertita: " + message[1] + "\n");
                        break;
                    case "VOWEL":
                        output_area.append("Il numero di vocali è: " + message[1] + "\n");
                        break;
                    case "ERROR":
                        if(message[1].equals("COMMAND"))
                            output_area.append("COMANDO INESISTENTE\n");
                        else if(message[1].equals("SYNTAX"))
                           output_area.append("Errore di sintassi\n"); 
                        break;
                    case "EXIT":
                        s.close();
                        System.exit(0);
                        break;
                    default:
                        //per altri casi d'uso futuri
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(StringClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
