/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Classe per l'interfaccia utente lato client. Un oggetto di questa classe
 * viene inserito nella App e la stessa App verra passata alla gui come
 * parametro insieme all'identificativo del client.
 *
 * @author palma
 */
public class ChatGui extends JFrame implements IAppObserver {

    /**
     * Il Model della lista utenti (la M dell'MVC)
     */
    private final DefaultListModel<String> usersModel;
    /**
     * Il Model della lista messaggi (la M dell'MVC)
     */
    private final DefaultListModel<String> messagesModel;

    private final JTextField text;
    /**
     * La lista dei messaggi (la V e la C dell'MVC)
     */
    private final JList<String> messagesList;
    /**
     * La lista degli utenti (la V e la C dell'MVC)
     */
    private final JList<String> usersList;

    /**
     * L'App che deve implementare l'@see IEventObserver
     */
    private IEventObserver observer;

    /**
     * Mappa per gestire gli errori
     */
    private Map<Integer, String> errors;
    private final JPopupMenu menu;

    /**
     * Nel costruttore passare l'App che deve implementare
     *
     * @see IEventObserver
     *
     * @param title Passare l'identificativo del client
     */
    public ChatGui(String title) {
        super(title);
        errors = new HashMap<>();
        errors.put(2, "Wrong parameter numbers");
        errors.put(1, "Unknown command");
        errors.put(0, "Not number format");
        messagesModel = new DefaultListModel<>();
        usersModel = new DefaultListModel<>();
        messagesList = new JList(messagesModel);
        messagesList.setBorder(BorderFactory.createTitledBorder("Message list"));
        usersList = new JList<>(usersModel);
        menu = new JPopupMenu();
        JMenuItem files = new JMenuItem("View files");
        files.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(ChatGui.this,usersList.getSelectedValue());
        });
        menu.add(files);
        usersList.setBorder(BorderFactory.createTitledBorder("Users list"));
        usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersList.addMouseListener(new MouseAdapter() {
            int selectedIndex = -1;

            @Override
            public void mouseClicked(MouseEvent e) {
                int index = usersList.locationToIndex(e.getPoint());
                if (index == selectedIndex) {
                    usersList.clearSelection();
                    selectedIndex = -1;
                } else {
                    selectedIndex = index;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }
        });
        text = new JTextField(55);
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyAndSend();
                }
            }
        });
        JButton send = new JButton("" + '\u23CE');
        send.addActionListener((ActionEvent e) -> {
            verifyAndSend();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                observer.sendQuit();
                dispose();
            }
        });

        JPanel south = new JPanel();
        south.add(text);
        south.add(send);
        add(south, BorderLayout.SOUTH);
        add(new JScrollPane(usersList), BorderLayout.EAST);
        add(new JScrollPane(messagesList));
        //setSize(700, 400);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showPopup(MouseEvent e) {
        int index = usersList.locationToIndex(e.getPoint());
        if (index >= 0 && usersList.isSelectedIndex(index)) {
            usersList.setSelectedIndex(index);
        }
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Per aggiungere un utente arrivato nella lista
     *
     * @param user L'utente da aggiungere
     */
    @Override
    public void addUser(String user) {
        usersModel.addElement(user);
        messagesModel.addElement(user + " join the chat");
    }

    /**
     * Per riempire la lsita utewnti quando ci si connette
     *
     * @param user Il singolo utente che man mano sarà aggiunto alla lista
     */
    @Override
    public void addUserToList(String user) {
        usersModel.addElement(user);
    }

    /**
     * Per rimuovere un utente che si sgancia
     *
     * @param user L'utente da rimuovere
     */
    @Override
    public void removeUser(String user) {
        usersModel.removeElement(user);
        messagesModel.addElement(user + " left the chat");
    }

    /**
     * Per aggiungere un messaggio alla lista
     *
     * @param user L'autore del messaggio
     * @param message Il messaggio
     */
    @Override
    public void addMessage(String user, String message) {
        messagesModel.addElement(user + " : " + message);
    }

    /**
     * Per aggiungere un messaggio privato alla lista dei messaggi
     *
     * @param from L'autopre del messaggio privato
     *
     * @param message Il messaggio privato
     */
    @Override
    public void addPrivateMessage(String from, String message) {
        messagesModel.addElement("Private from " + from + " : " + message);
    }

    @Override
    public void error(int errorCode) {
        JOptionPane.showMessageDialog(this, errors.get(errorCode), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void verifyAndSend() {
        if (!text.getText().trim().isEmpty()) {
            if (usersList.getSelectedIndex() < 0) {
                observer.sendMessage(text.getText());
            } else {
                String destination = usersList.getSelectedValue();
                observer.sendPrivateMessage(destination, text.getText());
                usersList.clearSelection();
            }
            text.setText("");
        }
    }
    
    public void setObserver(IEventObserver observer){
        this.observer=observer;
    }
}
