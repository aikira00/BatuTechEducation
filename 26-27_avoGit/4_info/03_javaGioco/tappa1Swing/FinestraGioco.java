package tappa1Swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// La finestra è una Vista e ascolta i suoi pulsanti. Le regole restano nel gioco.
public class FinestraGioco extends JFrame implements Vista, ActionListener {
    private JTextArea messaggi;
    private JButton attacca;
    private JButton fuggi;
    private PannelloVita barre;
    private Partita partita;

    public FinestraGioco() {
        super("Il dungeon");

        messaggi = new JTextArea(16, 40);
        messaggi.setEditable(false);
        messaggi.setLineWrap(true);
        attacca = new JButton("Attacca");
        fuggi = new JButton("Fuggi");
        attacca.addActionListener(this);
        fuggi.addActionListener(this);

        partita = new Partita(this);
        barre = new PannelloVita(partita);

        JPanel pulsanti = new JPanel();
        pulsanti.add(attacca);
        pulsanti.add(fuggi);

        add(barre, BorderLayout.NORTH);
        add(new JScrollPane(messaggi), BorderLayout.CENTER);
        add(pulsanti, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        partita.inizia();
    }

    @Override
    public void mostra(String messaggio) {
        messaggi.append(messaggio + "\n");
    }

    // Il while non c'è più: è il pulsante a far andare avanti il gioco.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attacca) {
            partita.attacca();
        } else {
            partita.fuggi();
        }
        barre.repaint();
        if (partita.isFinita()) {
            attacca.setEnabled(false);
            fuggi.setEnabled(false);
        }
    }
}
