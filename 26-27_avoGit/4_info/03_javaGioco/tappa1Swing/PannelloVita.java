package tappa1Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

// Le due barre della vita. paintComponent non lo chiamiamo noi: lo chiama Swing.
public class PannelloVita extends JPanel {
    private static final int PIXEL_PER_VITA = 12;

    private Partita partita;

    public PannelloVita(Partita partita) {
        this.partita = partita;
        setPreferredSize(new Dimension(400, 80));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Eroe eroe = partita.getEroe();
        disegnaBarra(g, eroe.getNome(), eroe.getVita(), 25, new Color(40, 140, 80));

        Mostro m = partita.getMostro();
        if (m != null) {
            disegnaBarra(g, m.getNome(), m.getVita(), 60, new Color(180, 60, 60));
        }
    }

    private void disegnaBarra(Graphics g, String nome, int vita, int y, Color colore) {
        g.setColor(Color.BLACK);
        g.drawString(nome + ": " + vita, 10, y);
        g.setColor(colore);
        g.fillRect(110, y - 12, vita * PIXEL_PER_VITA, 14);
    }
}
