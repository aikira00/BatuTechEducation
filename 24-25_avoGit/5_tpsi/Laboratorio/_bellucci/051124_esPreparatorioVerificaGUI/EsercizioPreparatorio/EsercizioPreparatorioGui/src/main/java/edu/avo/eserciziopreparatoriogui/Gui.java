/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatoriogui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author palma
 */
public class Gui extends JFrame implements IAppObserver {

    IEventObserver observer;
    JComboBox<String> combo;
    JTextField a;
    JTextField b;
    JTextField c;
    JPanel south;
    JTextField x;
    JTextField y;

    private final JPanel center;
    private final JLabel xLabel;
    private final JLabel yLabel;

    public Gui(String title) {
        super(title);
        center = new JPanel();
        String[] choises = {"Esplicita", "Ortogonale", "Parallela"};
        JLabel operazione = new JLabel("Operazione");
        combo = new JComboBox<>(choises);
        center.add(combo);
        JLabel aLabel = new JLabel("a");
        center.add(aLabel);
        a = new JTextField(5);
        center.add(a);
        JLabel bLabel = new JLabel("b");
        center.add(bLabel);
        b = new JTextField(5);
        center.add(b);
        JLabel cLabel = new JLabel("c");
        center.add(cLabel);
        c = new JTextField(5);
        center.add(c);
        add(center);
        south = new JPanel();
        add(south, BorderLayout.SOUTH);
        x = new JTextField(5);
        y = new JTextField(5);
        xLabel = new JLabel("x");
        yLabel = new JLabel("y");
        JButton send = new JButton("Invia");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (combo.getSelectedIndex()) {
                    case 0 -> {
                        observer.sendEsplicita(Double.parseDouble(a.getText()),
                                Double.parseDouble(b.getText()), Double.parseDouble(c.getText()));
                    }
                    case 1 -> {
                        observer.sendOrtogonale(Double.parseDouble(a.getText()),
                                Double.parseDouble(b.getText()), Double.parseDouble(c.getText()),
                                Double.parseDouble(x.getText()), Double.parseDouble(y.getText()));
                    }
                    case 2 -> {
                        observer.sendParallela(Double.parseDouble(a.getText()),
                                Double.parseDouble(b.getText()), Double.parseDouble(c.getText()),
                                Double.parseDouble(x.getText()), Double.parseDouble(y.getText()));
                    }
                }
            }
        });
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                south.removeAll();
                if (combo.getSelectedIndex() == 1 || combo.getSelectedIndex() == 2) {
                    x.setText("");
                    y.setText("");
                    a.setText("");
                    b.setText("");
                    c.setText("");
                    south.add(xLabel);
                    south.add(x);
                    south.add(yLabel);
                    south.add(y);
                }
                validate();
                pack();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                observer.sendFine();
                dispose();
            }
        });
        center.add(send);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void updateEsplicita(double m, double q) {
        SwingUtilities.invokeLater(() -> {
            south.removeAll();
            JLabel result = new JLabel("Esplicita:  m=" + m + "   q=" + q);
            south.add(result);
            validate();
            pack();
        });
    }

    @Override
    public void updateEsplicita(double x) {
        SwingUtilities.invokeLater(() -> {
            south.removeAll();
            JLabel result = new JLabel("Esplicita:  x=" + x);
            south.add(result);
            validate();
            pack();
        });
    }

    @Override
    public void updateParallela(double a, double b, double c) {
        SwingUtilities.invokeLater(() -> {
            south.removeAll();
            JLabel result = new JLabel("Parallela: a=" + a + "  b=" + b + "  c=" + c);
            south.add(result);
            validate();
            pack();
        });
    }

    @Override
    public void updateOrtogonale(double a, double b, double c) {
        SwingUtilities.invokeLater(() -> {
            south.removeAll();
            JLabel result = new JLabel("Ortogonale: a=" + a + "  b=" + b + "  c=" + c);
            south.add(result);
            validate();
            pack();
        });
    }

    @Override
    public void updateError(String type) {
        south.removeAll();
        JOptionPane.showMessageDialog(this, type, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void closeWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dispose();
    }

    @Override
    public void setObserver(IEventObserver observer) {
        this.observer = observer;
    }
}
