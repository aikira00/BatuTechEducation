import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GiocoAutomobili extends JFrame {
    private static final int LARGHEZZA = 800;
    private static final int ALTEZZA = 500;
    private static final int NUM_AUTO = 5;
    private static final int LUNGHEZZA_AUTO = 60;
    private static final int ALTEZZA_AUTO = 30;
    private static final int LUNGHEZZA_PISTA = 700;

    private Automobile[] automobili;
    private boolean garaInCorso = false;
    private boolean garaFinita = false;
    private String vincitore = "";
    private JButton btnAvvia;

    public GiocoAutomobili() {
        setTitle("Gara di Automobili");
        setSize(LARGHEZZA, ALTEZZA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inizializzazione delle automobili
        automobili = new Automobile[NUM_AUTO];
        String[] nomiAuto = {"Rossa", "Blu", "Verde", "Gialla", "Nera"};
        Color[] coloriAuto = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK};

        for (int i = 0; i < NUM_AUTO; i++) {
            automobili[i] = new Automobile(
                    10,  // posizione iniziale X
                    50 + i * (ALTEZZA_AUTO + 20),  // posizione Y (corsie distanziate)
                    nomiAuto[i],
                    coloriAuto[i]
            );
        }

        // Pannello di gara
        JPanel pistaDiGara = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Disegna linea di partenza
                g.setColor(Color.DARK_GRAY);
                g.fillRect(10, 40, 5, ALTEZZA - 100);

                // Disegna linea di arrivo
                g.setColor(Color.DARK_GRAY);
                g.fillRect(LUNGHEZZA_PISTA, 40, 5, ALTEZZA - 100);

                // Disegna corsie
                g.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < NUM_AUTO; i++) {
                    int y = 50 + i * (ALTEZZA_AUTO + 20);
                    g.drawLine(10, y + ALTEZZA_AUTO, LUNGHEZZA_PISTA, y + ALTEZZA_AUTO);
                }

                // Disegna automobili
                for (Automobile auto : automobili) {
                    auto.disegna(g);
                }

                // Mostra messaggio vincitore
                if (garaFinita) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString("ARRIVO! L'auto " + vincitore + " ha vinto!", 250, 30);
                }
            }
        };
        pistaDiGara.setBackground(new Color(230, 230, 230));
        add(pistaDiGara, BorderLayout.CENTER);

        // Pannello di controllo
        JPanel panControlli = new JPanel();
        btnAvvia = new JButton("Avvia Gara");
        btnAvvia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!garaInCorso) {
                    avviaGara();
                    btnAvvia.setText("Gara in corso...");
                    btnAvvia.setEnabled(false);
                }
            }
        });

        JButton btnReset = new JButton("Nuova Gara");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGara();
            }
        });

        panControlli.add(btnAvvia);
        panControlli.add(btnReset);
        add(panControlli, BorderLayout.SOUTH);
    }

    private void avviaGara() {
        garaInCorso = true;
        garaFinita = false;
        vincitore = "";

        // Avvia un thread per ogni automobile
        for (Automobile auto : automobili) {
            Thread threadAuto = new Thread(new ThreadAutomobile(auto));
            threadAuto.start();
        }
    }

    private void resetGara() {
        garaInCorso = false;
        garaFinita = false;
        vincitore = "";

        // Riporta le auto alla posizione di partenza
        for (int i = 0; i < NUM_AUTO; i++) {
            automobili[i].x = 10;
        }

        btnAvvia.setText("Avvia Gara");
        btnAvvia.setEnabled(true);
        repaint();
    }

    // Thread per il movimento dell'automobile
    private class ThreadAutomobile implements Runnable {
        private Automobile auto;
        private Random random = new Random();

        public ThreadAutomobile(Automobile auto) {
            this.auto = auto;
        }

        @Override
        public void run() {
            while (garaInCorso && auto.x < LUNGHEZZA_PISTA - LUNGHEZZA_AUTO) {
                // Calcola una velocità casuale per simulare accelerazioni e rallentamenti
                int velocita = random.nextInt(5) + 1;
                auto.x += velocita;

                // Aggiorna la vista
                repaint();

                try {
                    // Tempo variabile tra i movimenti per simulare diverse prestazioni
                    Thread.sleep(50 + random.nextInt(30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Verifica se questa auto è arrivata prima (thread winner)
            synchronized (GiocoAutomobili.this) {
                if (!garaFinita) {
                    garaFinita = true;
                    vincitore = auto.nome;
                    garaInCorso = false;
                    SwingUtilities.invokeLater(() -> {
                        btnAvvia.setText("Avvia Gara");
                        btnAvvia.setEnabled(true);
                        repaint();
                    });
                }
            }
        }
    }

    // Classe Automobile
    private class Automobile {
        int x, y;
        String nome;
        Color colore;

        public Automobile(int x, int y, String nome, Color colore) {
            this.x = x;
            this.y = y;
            this.nome = nome;
            this.colore = colore;
        }

        public void disegna(Graphics g) {
            // Disegna il corpo dell'auto
            g.setColor(colore);
            g.fillRect(x, y, LUNGHEZZA_AUTO, ALTEZZA_AUTO);

            // Disegna i finestrini
            g.setColor(Color.CYAN);
            g.fillRect(x + 15, y + 5, 20, ALTEZZA_AUTO - 10);

            // Disegna le ruote
            g.setColor(Color.BLACK);
            g.fillOval(x + 10, y - 5, 10, 10);
            g.fillOval(x + LUNGHEZZA_AUTO - 20, y - 5, 10, 10);
            g.fillOval(x + 10, y + ALTEZZA_AUTO - 5, 10, 10);
            g.fillOval(x + LUNGHEZZA_AUTO - 20, y + ALTEZZA_AUTO - 5, 10, 10);

            // Disegna il nome
            g.setColor(Color.WHITE);
            g.drawString(nome, x + 10, y + ALTEZZA_AUTO / 2 + 5);
        }
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GiocoAutomobili().setVisible(true);
        });
    }
}