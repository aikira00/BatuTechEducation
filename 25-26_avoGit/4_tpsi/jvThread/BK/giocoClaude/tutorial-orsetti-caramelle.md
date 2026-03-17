# Tutorial: Creazione del gioco "Orsetti Golosi" con Thread in Java

Questo tutorial vi guiderà nella creazione di un divertente gioco Java usando i thread, dove degli orsetti si muovono autonomamente per raccogliere caramelle. L'orsetto che mangia più caramelle entro il tempo prestabilito vince!

## Indice
1. [Panoramica del progetto](#1-panoramica-del-progetto)
2. [Impostazione della struttura base](#2-impostazione-della-struttura-base)
3. [Creazione degli oggetti di gioco](#3-creazione-degli-oggetti-di-gioco)
4. [Implementazione dei thread](#4-implementazione-dei-thread)
5. [Creazione dell'interfaccia grafica](#5-creazione-dellinterfaccia-grafica)
6. [Gestione della logica di gioco](#6-gestione-della-logica-di-gioco)
7. [Test e debugging](#7-test-e-debugging)
8. [Sfide ed estensioni](#8-sfide-ed-estensioni)

## 1. Panoramica del progetto

### Funzionalità principali:
- 4 orsetti colorati si muovono autonomamente nella finestra di gioco
- Gli orsetti cercano di "mangiare" le caramelle sparse nel campo
- Ogni orsetto è gestito da un thread separato che ne controlla il movimento
- Il gioco dura 30 secondi, dopo i quali viene dichiarato il vincitore
- Le caramelle vengono generate automaticamente durante il gioco

### Concetti di programmazione:
- Threading in Java
- Sincronizzazione di risorse condivise
- Interfaccia grafica con Swing
- Collision detection
- Timer e gestione degli eventi

## 2. Impostazione della struttura base

Per prima cosa, creiamo la classe principale del nostro gioco che estenderà `JFrame`:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class OrsettiGolosi extends JFrame {
    // Costanti
    private static final int LARGHEZZA = 800;
    private static final int ALTEZZA = 600;
    private static final int NUM_ORSETTI = 4;
    private static final int NUM_CARAMELLE = 50;
    private static final int DIMENSIONE_ORSETTO = 40;
    private static final int DIMENSIONE_CARAMELLA = 20;
    private static final int DURATA_GIOCO = 30; // secondi
    
    // Variabili di stato
    private ArrayList<Orsetto> orsetti = new ArrayList<>();
    private ArrayList<Caramella> caramelle = new ArrayList<>();
    private Random random = new Random();
    private boolean giocoInCorso = false;
    private int tempoRimanente = DURATA_GIOCO;
    private Timer timerGioco;
    private JLabel lblTempo;
    private JButton btnAvvia;
    
    public OrsettiGolosi() {
        setTitle("Orsetti Golosi");
        setSize(LARGHEZZA, ALTEZZA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Il codice di inizializzazione verrà aggiunto nelle prossime fasi
    }
    
    // Il metodo main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OrsettiGolosi().setVisible(true);
        });
    }
}
```

## 3. Creazione degli oggetti di gioco

### Passo 3.1: Creiamo la classe Orsetto

Aggiungiamo una classe interna per rappresentare gli orsetti:

```java
// Classe Orsetto
private class Orsetto {
    int x, y;
    Color colore;
    String nome;
    int caramelleMangiate;
    
    public Orsetto(int x, int y, Color colore, String nome) {
        this.x = x;
        this.y = y;
        this.colore = colore;
        this.nome = nome;
        this.caramelleMangiate = 0;
    }
    
    public void disegna(Graphics g) {
        // Disegna il corpo dell'orsetto
        g.setColor(colore);
        g.fillOval(x, y, DIMENSIONE_ORSETTO, DIMENSIONE_ORSETTO);
        
        // Disegna le orecchie
        g.fillOval(x - 5, y - 5, 15, 15);
        g.fillOval(x + DIMENSIONE_ORSETTO - 10, y - 5, 15, 15);
        
        // Disegna il muso - con valori RGB validi
        int r = Math.max(0, colore.getRed() - 30);
        int g2 = Math.max(0, colore.getGreen() - 30);
        int b = Math.max(0, colore.getBlue() - 30);
        g.setColor(new Color(r, g2, b));
        g.fillOval(x + 10, y + 10, DIMENSIONE_ORSETTO - 20, DIMENSIONE_ORSETTO - 20);
        
        // Disegna gli occhi
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 15, 5, 5);
        g.fillOval(x + DIMENSIONE_ORSETTO - 15, y + 15, 5, 5);
        
        // Disegna il naso
        g.fillOval(x + DIMENSIONE_ORSETTO/2 - 3, y + 22, 6, 6);
    }
}
```

### Passo 3.2: Creiamo la classe Caramella

Aggiungiamo una classe interna per rappresentare le caramelle:

```java
// Classe Caramella
private class Caramella {
    int x, y;
    Color colore;
    
    public Caramella(int x, int y, Color colore) {
        this.x = x;
        this.y = y;
        this.colore = colore;
    }
    
    public void disegna(Graphics g) {
        g.setColor(colore);
        g.fillOval(x, y, DIMENSIONE_CARAMELLA, DIMENSIONE_CARAMELLA);
        
        // Disegna effetto "incarto"
        g.setColor(Color.WHITE);
        g.drawLine(x + 5, y + 5, x + DIMENSIONE_CARAMELLA - 5, y + DIMENSIONE_CARAMELLA - 5);
        g.drawLine(x + DIMENSIONE_CARAMELLA - 5, y + 5, x + 5, y + DIMENSIONE_CARAMELLA - 5);
    }
}
```

### Passo 3.3: Inizializzazione degli orsetti

Aggiungiamo questo codice al costruttore della classe principale:

```java
// Inizializza orsetti con colori diversi
Color[] coloriOrsetti = {Color.PINK, Color.BLUE, Color.ORANGE, Color.GREEN};
String[] nomiOrsetti = {"Rosa", "Blu", "Arancio", "Verde"};

for (int i = 0; i < NUM_ORSETTI; i++) {
    int x = random.nextInt(LARGHEZZA - DIMENSIONE_ORSETTO);
    int y = random.nextInt(ALTEZZA - 100 - DIMENSIONE_ORSETTO);
    orsetti.add(new Orsetto(x, y, coloriOrsetti[i], nomiOrsetti[i]));
}
```

### Passo 3.4: Metodo per generare caramelle

Aggiungiamo un metodo per creare nuove caramelle:

```java
private void generaNuovaCaramella() {
    int x = random.nextInt(LARGHEZZA - DIMENSIONE_CARAMELLA);
    int y = random.nextInt(ALTEZZA - 100 - DIMENSIONE_CARAMELLA);
    
    // Genera un colore pastello casuale per la caramella
    int r = 100 + random.nextInt(156); // 100-255
    int g = 100 + random.nextInt(156); // 100-255
    int b = 100 + random.nextInt(156); // 100-255
    Color colore = new Color(r, g, b);
    
    caramelle.add(new Caramella(x, y, colore));
}
```

## 4. Implementazione dei thread

### Passo 4.1: Thread per gli orsetti

Aggiungiamo una classe interna `ThreadOrsetto` che implementa `Runnable`:

```java
// Thread per il movimento degli orsetti
private class ThreadOrsetto implements Runnable {
    private Orsetto orsetto;
    
    public ThreadOrsetto(Orsetto orsetto) {
        this.orsetto = orsetto;
    }
    
    @Override
    public void run() {
        while (giocoInCorso) {
            // Trova la caramella più vicina
            Caramella caramellaVicina = trovaCamellaPiuVicina(orsetto);
            
            if (caramellaVicina != null) {
                // Muovi l'orsetto verso la caramella
                if (orsetto.x < caramellaVicina.x) orsetto.x += 2;
                else if (orsetto.x > caramellaVicina.x) orsetto.x -= 2;
                
                if (orsetto.y < caramellaVicina.y) orsetto.y += 2;
                else if (orsetto.y > caramellaVicina.y) orsetto.y -= 2;
                
                // Controlla se l'orsetto ha raggiunto la caramella
                if (distanza(orsetto.x, orsetto.y, caramellaVicina.x, caramellaVicina.y) < 20) {
                    synchronized (caramelle) {
                        if (caramelle.contains(caramellaVicina)) {
                            caramelle.remove(caramellaVicina);
                            orsetto.caramelleMangiate++;
                        }
                    }
                }
            } else {
                // Movimento casuale se non ci sono caramelle
                orsetto.x += random.nextInt(5) - 2;
                orsetto.y += random.nextInt(5) - 2;
            }
            
            // Mantieni l'orsetto dentro i bordi
            orsetto.x = Math.max(0, Math.min(orsetto.x, LARGHEZZA - DIMENSIONE_ORSETTO));
            orsetto.y = Math.max(0, Math.min(orsetto.y, ALTEZZA - 100 - DIMENSIONE_ORSETTO));
            
            repaint();
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private Caramella trovaCamellaPiuVicina(Orsetto orsetto) {
        Caramella piuVicina = null;
        double distanzaMinima = Double.MAX_VALUE;
        
        synchronized (caramelle) {
            for (Caramella caramella : caramelle) {
                double d = distanza(orsetto.x, orsetto.y, caramella.x, caramella.y);
                if (d < distanzaMinima) {
                    distanzaMinima = d;
                    piuVicina = caramella;
                }
            }
        }
        
        return piuVicina;
    }
}
```

### Passo 4.2: Thread per generare caramelle

Aggiungiamo una classe interna per generare nuove caramelle:

```java
// Thread per generare nuove caramelle
private class ThreadGeneraCaramelle implements Runnable {
    @Override
    public void run() {
        while (giocoInCorso) {
            synchronized (caramelle) {
                if (caramelle.size() < NUM_CARAMELLE / 2) {
                    generaNuovaCaramella();
                }
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Passo 4.3: Funzione per calcolare la distanza

Aggiungiamo un metodo di utilità per calcolare la distanza tra due punti:

```java
// Calcola la distanza tra due punti
private double distanza(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
}
```

## 5. Creazione dell'interfaccia grafica

### Passo 5.1: Pannello di gioco

Aggiungiamo questo codice al costruttore della classe principale:

```java
// Pannello di gioco
JPanel pannelloGioco = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Disegna sfondo
        g.setColor(new Color(255, 250, 240)); // Colore crema
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Disegna caramelle
        for (Caramella caramella : caramelle) {
            caramella.disegna(g);
        }
        
        // Disegna orsetti
        for (Orsetto orsetto : orsetti) {
            orsetto.disegna(g);
        }
        
        // Disegna punteggi
        g.setFont(new Font("Arial", Font.BOLD, 14));
        for (int i = 0; i < orsetti.size(); i++) {
            Orsetto orsetto = orsetti.get(i);
            g.setColor(orsetto.colore);
            g.drawString("Orsetto " + orsetto.nome + ": " + orsetto.caramelleMangiate, 
                         10, 20 + i * 20);
        }
        
        // Se il gioco è finito, mostra il vincitore
        if (!giocoInCorso && tempoRimanente == 0) {
            Orsetto vincitore = trovaVincitore();
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("VINCITORE: Orsetto " + vincitore.nome + 
                         " con " + vincitore.caramelleMangiate + " caramelle!", 
                         LARGHEZZA/2 - 250, ALTEZZA/2);
        }
    }
};
pannelloGioco.setBackground(new Color(255, 250, 240));
add(pannelloGioco, BorderLayout.CENTER);
```