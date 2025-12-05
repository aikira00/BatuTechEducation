import java.net.Socket;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;



public class DataClient extends JFrame {
    private final JTextField input;
    private final JTextArea output;
    private final JButton invia;

    public DataClient() {
        setTitle("");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        input = new JTextField();
        output = new JTextArea();
        output.setEditable(false);
        invia = new JButton("Invia");

        add(input, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        add(invia, BorderLayout.SOUTH);

        invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaRichiesta();
            }
        });
        setVisible(true);
    };


    private void inviaRichiesta() {
        String richiesta = input.getText();
        try {
            Socket s = new Socket("localhost", 6161);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
        s.close();
        } catch (IOException e) {
            output.append("Errore di connessione" + e.getMessage());
        }

    }

    public static void main(String[] args) {
        new DataClient();
    }

}



 