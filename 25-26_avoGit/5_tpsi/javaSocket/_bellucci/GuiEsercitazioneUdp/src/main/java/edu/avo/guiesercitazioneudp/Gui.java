/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.guiesercitazioneudp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author palma
 */
public class Gui extends JFrame implements IAppObserver {

    private JComboBox<String> combo;
    private IEventObserver observer;
    private Manager manager;
    private JPanel south;
    private Map<Byte, String> errorMap;
    private JComboBox temp;

    public Gui(String title) {
        super(title);
        String[] components = new String[11];
        for (int i = 0; i < components.length; i++) {
            components[i] = "Stazione " + (i + 1);
        }
        errorMap = new HashMap<>();
        errorMap.put((byte) 1, "Comando sconosciuto");
        errorMap.put((byte) 2, "Numero stazione errato ");
        errorMap.put((byte) 3, "Numero sensore errato ");
        errorMap.put((byte) 4, "Numero attuatore errato ");
        errorMap.put((byte) 5, "Numero parametri errato ");
        errorMap.put((byte) 6, "Non connesso a una stazione ");
        combo = new JComboBox<>(components);
        combo.setSelectedIndex(0);
        JButton connect = new JButton("Connessione");
        connect.setActionCommand("C");
        manager = new Manager();
        connect.addActionListener(manager);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                observer.sendClose();
                dispose();
            }
        });
        south = new JPanel();
        JPanel center = new JPanel();
        center.add(combo);
        center.add(connect);
        add(center);
        add(south, BorderLayout.SOUTH);
        JButton setting = new JButton("Imposta Valore");
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        temp = new JComboBox(values);
        temp.setSelectedIndex(0);
        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=JOptionPane.showInputDialog(Gui.this, "Inserire il valore da impostare (0-255");
                if(s==null){
                    observer.sendSetting((byte) (temp.getSelectedIndex() + 1));
                } else{
                    observer.sendSetting((byte) (temp.getSelectedIndex() + 1),Byte.parseByte(s));
                }
            }
        });
        south.add(temp);
        south.add(setting);
        JButton request = new JButton("Leggi valore");
        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result=JOptionPane.showConfirmDialog(Gui.this, "Vuoi verificare l'errore di comandos conosciuto?", "Lettura", JOptionPane.YES_NO_OPTION);
                if(result==JOptionPane.YES_OPTION){
                    observer.sendUnknowCommand();
                }else{
                    observer.sendRequest((byte) (temp.getSelectedIndex() + 1));
                }
            }
        });
        south.add(request);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void updateConnect(boolean ok) {
        if (ok) {
            JOptionPane.showMessageDialog(this, "Connesso a " + combo.getSelectedItem());
        } else {
            JOptionPane.showMessageDialog(this, "Errore nella connessione a " + combo.getSelectedItem());
        }
    }

    @Override
    public void updateSetting(int what, int value) {
        if (manager.getSelected() == 1) {
            JOptionPane.showMessageDialog(this, "L'attutatore " + temp.getSelectedItem() + " impostato a " + value);
        }
    }

    @Override
    public void updateRequest(int what, int value) {
        JOptionPane.showMessageDialog(this, "Il sensore " + temp.getSelectedItem() + " ha valore " + value);
    }

    @Override
    public void setObserver(IEventObserver observer) {
        this.observer = observer;
    }

    @Override
    public void updateDisconnect() {
        JOptionPane.showMessageDialog(this, "diconnesso da " + combo.getSelectedItem(), "Informazioni", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void updateError(byte errorType) {
        JOptionPane.showMessageDialog(this, errorMap.get(errorType), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    class Manager implements ActionListener {

        public int selected = -1;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("C")) {
                selected = combo.getSelectedIndex() + 1;
                observer.sendConnect((byte) selected);
                combo.setEnabled(false);
                ((JButton) e.getSource()).setActionCommand("D");
                ((JButton) e.getSource()).setText("Disconnessione");
            } else {
                ((JButton) e.getSource()).setActionCommand("C");
                ((JButton) e.getSource()).setText("Connessione");
                observer.sendDisconnect();
                combo.setEnabled(true);
                combo.revalidate();
            }

        }

        public int getSelected() {
            return selected;
        }
    }
}
