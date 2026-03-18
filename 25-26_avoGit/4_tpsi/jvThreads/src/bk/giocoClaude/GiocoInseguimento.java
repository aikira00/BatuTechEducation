import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GiocoInseguimento extends JFrame {
    private static final int LARGHEZZA = 600;
    private static final int ALTEZZA = 400;
    private static final int DIMENSIONE_PERSONAGGIO = 20;
    
    private Personaggio predatore;
    private Personaggio preda;
    
    public GiocoInseguimento() {
        setTitle("Gioco di Inseguimento");
        setSize(LARGHEZZA, ALTEZZA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inizializzazione dei personaggi in posizioni casuali
        Random random = new Random();
        predatore = new Personaggio(
                random.nextInt(LARGHEZZA - DIMENSIONE_PERSONAGGIO),
                random.nextInt(ALTEZZA - DIMENSIONE_PERSONAGGIO), 
                Color.RED,
                "Predatore");
        
        // La preda inizia lontana dal predatore
        preda = new Personaggio(
                (predatore.x + LARGHEZZA/2) % LARGHEZZA,
                (predatore.y + ALTEZZA/2) % ALTEZZA, 
                Color.BLUE,
                "Preda");
        
        // Pannello di gioco
        JPanel pannelloGioco = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                predatore.disegna(g);
                preda.disegna(g);
            }
        };
        pannelloGioco.setBackground(Color.WHITE);
        add(pannelloGioco);
        
        // Avvio dei thread
        Thread threadPredatore = new Thread(new ThreadPredatore());
        Thread threadPreda = new Thread(new ThreadPreda());
        
        threadPredatore.start();
        threadPreda.start();
    }
    
    // Thread per il movimento del predatore
    private class ThreadPredatore implements Runnable {
        @Override
        public void run() {
            while (true) {
                // Il predatore si muove verso la preda
                if (predatore.x < preda.x) predatore.x += 1;
                else if (predatore.x > preda.x) predatore.x -= 1;
                
                if (predatore.y < preda.y) predatore.y += 1;
                else if (predatore.y > preda.y) predatore.y -= 1;
                
                // Controllo cattura
                if (distanza(predatore, preda) < DIMENSIONE_PERSONAGGIO) {
                    JOptionPane.showMessageDialog(null, 
                        "Il predatore ha catturato la preda!", 
                        "Fine Gioco", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                
                repaint();
                
                try {
                    Thread.sleep(50);  // Velocità del predatore
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Thread per il movimento della preda
    private class ThreadPreda implements Runnable {
        private Random random = new Random();
        
        @Override
        public void run() {
            while (true) {
                // La preda cerca di scappare in modo semi-intelligente
                if (distanza(predatore, preda) < 100) {
                    // Se il predatore è vicino, scappa nella direzione opposta
                    if (predatore.x < preda.x) preda.x += 2;
                    else preda.x -= 2;
                    
                    if (predatore.y < preda.y) preda.y += 2;
                    else preda.y -= 2;
                } else {
                    // Altrimenti si muove casualmente
                    preda.x += random.nextInt(3) - 1;
                    preda.y += random.nextInt(3) - 1;
                }
                
                // Mantieni all'interno dei bordi
                preda.x = Math.max(0, Math.min(preda.x, LARGHEZZA - DIMENSIONE_PERSONAGGIO));
                preda.y = Math.max(0, Math.min(preda.y, ALTEZZA - DIMENSIONE_PERSONAGGIO));
                
                repaint();
                
                try {
                    Thread.sleep(30);  // Velocità della preda (leggermente più veloce del predatore)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Calcola la distanza tra due personaggi
    private double distanza(Personaggio p1, Personaggio p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    
    // Classe Personaggio
    private class Personaggio {
        int x, y;
        Color colore;
        String nome;
        
        public Personaggio(int x, int y, Color colore, String nome) {
            this.x = x;
            this.y = y;
            this.colore = colore;
            this.nome = nome;
        }
        
        public void disegna(Graphics g) {
            g.setColor(colore);
            g.fillOval(x, y, DIMENSIONE_PERSONAGGIO, DIMENSIONE_PERSONAGGIO);
            g.setColor(Color.BLACK);
            g.drawString(nome, x, y - 5);
        }
    }
    
    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GiocoInseguimento().setVisible(true);
        });
    }
}
