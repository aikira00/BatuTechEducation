/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author adamr
 */
public class CalcClient extends JFrame implements ActionListener, Runnable {

    private JLabel calcola_label = new JLabel("Calcola: ");
    private JTextField inserimento_field = new JTextField("");
    private JButton invia_button = new JButton("INVIA");
    private JButton user_button = new JButton("USERNAME");
    private JLabel titolo_label = new JLabel("CALCOLATRICE");
    private String user_name;
    private Socket s;

    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(270, 200);
        this.setVisible(true);
        this.setResizable(false);
        titolo_label.setFont(new Font("Myriad Pro", Font.BOLD, 20));
        titolo_label.setBounds(50, 30, 170, 20);
        invia_button.setBounds(160, 60, 90, 40);
        user_button.setBounds(10, 110, 240, 40);
        calcola_label.setBounds(10, 60, 90, 40);
        inserimento_field.setBounds(60, 60, 90, 40);
        this.add(titolo_label);
        this.add(calcola_label);
        this.add(inserimento_field);
        this.add(invia_button);
        this.add(user_button);
        invia_button.addActionListener(this);
        user_button.addActionListener(this);
        try {
            s = new Socket("localhost", 10000);
        } catch (IOException ex) {
            Logger.getLogger(CalcClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrintWriter out;
        BufferedReader in;
        if (e.getSource() == invia_button) {
            try {
                float risultato;
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out.println(inserimento_field.getText());
                System.out.println("INVIATO AL SERVER " + inserimento_field.getText());
                String controllo = in.readLine();
                if (controllo != null) {
                    JDialog d = new JDialog(this, "RISULTATO");
                    d.setSize(100, 100);
                    d.setResizable(false);
                    String[] errOrsucc = controllo.split(";");
                    if(errOrsucc[0].equals("ERRORE")){
                        if (errOrsucc[1].equals("00")) {
                            JLabel label_result = new JLabel("ERR NUM/FOR");
                            d.add(label_result);
                            d.setVisible(true);
                        } else if (errOrsucc[1].equals("01")) {
                            JLabel label_result = new JLabel("ERR /0");
                            d.add(label_result);
                            d.setVisible(true);
                        } else if (errOrsucc[1].equals("02")) {
                            JLabel label_result = new JLabel("ERR GEN");
                            d.add(label_result);
                            d.setVisible(true);
                        }
                    } 
                    else {
                        if (controllo.equals("EXIT")) {
                            s.close();
                            System.exit(0);
                        }
                        else if(controllo.equals("SUCCESS")){
                            JLabel label_result = new JLabel("SUCCESS");
                            d.add(label_result);
                            d.setVisible(true);
                        }
                        else if (controllo.length() > 0) {
                            risultato = Float.parseFloat(controllo);
                            JLabel label_result = new JLabel("Il risultato è " + risultato);

                            label_result.setBounds(20, 30, 10, 10);

                            d.add(label_result);
                            d.setVisible(true);

                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CalcClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == user_button) {
            user_name = inserimento_field.getText();
            try {
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out.println(user_name);
                System.out.println("INVIATO AL SERVER " + user_name);
            } catch (IOException ex) {
                Logger.getLogger(CalcClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
