/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author palma
 */
public class View extends JFrame implements IApplicationObserver {

    private JLabel min;
    private JLabel max;
    private JButton start;
    private JButton attempt;
    private JButton quit;
    private Controller controller;
    private final JButton parErr;
    private final JButton unkComm;

    public View(String title, Controller controller) {
        super(title);
        this.controller=controller;
        JLabel labelMin = new JLabel("Minimo");
        JLabel labelMax = new JLabel("Massimo");
        min = new JLabel("    ");
        max = new JLabel("    ");
        start = new JButton("Comincia");
        quit = new JButton("Uscita");
        attempt = new JButton("Indovina");
        attempt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int n=Integer.parseInt(JOptionPane.showInputDialog(View.this,"Inserire il numero"));
                    controller.sendAttempt(n);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(View.this, "Errore: non hai inserito un numero","Errore",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        attempt.setEnabled(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendStart();
                attempt.setEnabled(true);
                parErr.setEnabled(true);
                unkComm.setEnabled(true);
            }
        });
        JPanel north = new JPanel();
        north.add(labelMin);
        north.add(min);
        north.add(labelMax);
        north.add(max);
        add(north, BorderLayout.NORTH);
        JPanel south = new JPanel();
        parErr=new JButton("Errore nei parametri");
        unkComm=new JButton("Comando Sconosciuto");
        parErr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendParametersError();
            }
        });
        unkComm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendUnknownCommand();
            }
        });
        south.add(start);
        south.add(attempt);
        south.add(quit);
        south.add(parErr);
        south.add(unkComm);
        parErr.setEnabled(false);
        unkComm.setEnabled(false);
        add(south, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.sendQuit();
                dispose();
            }
        });
        quit.addActionListener((ActionEvent e) -> {
            View.this.dispatchEvent(new WindowEvent(View.this, WindowEvent.WINDOW_CLOSING));
        });
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void viewLimits(int min, int max) {
        this.min.setText("" + min);
        this.max.setText("" + max);
    }

    @Override
    public void viewResult(String result) {
        String msg="Valore troppo basso";
        if(result.equals("greather")){
            msg="Troppo alto";
        }
        JOptionPane.showMessageDialog(View.this, msg);
    }

    @Override
    public void viewWon() {
        JButton button=quit;
        int answer=JOptionPane.showConfirmDialog(this, "Hai vinto. Vuoi giocare ancora?", "Messaggio di vitoria", JOptionPane.YES_NO_OPTION);
        if(answer==JOptionPane.OK_OPTION){
            button=start;
        }
        button.doClick(200);
    }

    @Override
    public void viewParametersError() {
        JOptionPane.showMessageDialog(this, "Errori nei parametri","Error",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void viewUnknownCommand() {
        JOptionPane.showMessageDialog(this, "Comando sconosciuto","Error",JOptionPane.ERROR_MESSAGE);
    }

}
