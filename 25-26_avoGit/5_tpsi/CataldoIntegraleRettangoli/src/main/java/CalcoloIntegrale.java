
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalcoloIntegrale {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel inserisciFunzione = new JLabel("INSERISCI LA FUNZIONE");   //SCRITTA PER INSERIMENTO FUNZIONE
        frame.add(inserisciFunzione);
        inserisciFunzione.setBounds(100, 150, 300, 50);

        JTextField jtxt1 = new JTextField();    //TEXTFIELD INSERIMENTO FUNZIONE
        frame.add(jtxt1);
        jtxt1.setBounds(100, 200, 100, 20);

        JButton calcola = new JButton("CALCOLA");  //BOTTONE CALCOLA
        frame.add(calcola);
        calcola.setBounds(100, 50, 150, 75);
        
        JLabel inserisciIntervallo = new JLabel("INSERISCI L'INTERVALLO");
        frame.add(inserisciIntervallo);
        inserisciIntervallo.setBounds(100, 250, 300, 50);
        
        JLabel inserisciA = new JLabel("A:");       //SCRITTA PER INSERIMENTO INTERVALLO
        frame.add(inserisciA);
        inserisciA.setBounds(100, 300, 300, 50);
        
        JTextField jtxt2 = new JTextField();    //TEXTFIELD INSERIMENTO A
        frame.add(jtxt2);
        jtxt2.setBounds(130, 315, 100, 20);
        
        JLabel inserisciB = new JLabel("B:");       //SCRITTA PER INSERIMENTO INTERVALLO
        frame.add(inserisciB);
        inserisciB.setBounds(100, 340, 300, 50);
        
        JTextField jtxt3 = new JTextField();    //TEXTFIELD INSERIMENTO B
        frame.add(jtxt3);
        jtxt3.setBounds(130, 355, 100, 20);
        
        JLabel inserisciRettangoli= new JLabel("INSERISCI IL NUMERO DI RETTANGOLI:");       //SCRITTA PER INSERIMENTO N RETTANGOLI
        frame.add(inserisciRettangoli);
        inserisciRettangoli.setBounds(100, 400, 300, 50);
        
        JTextField jtxt4 = new JTextField();    //TEXTFIELD INSERIMENTO NUMERO RETTANGOLI
        frame.add(jtxt4);
        jtxt4.setBounds(100, 450, 100, 20);
        
        JTextArea textArea = new JTextArea();       //TEXTAREA PER RISULTATO
        textArea.setEditable(false);
        frame.add(textArea);
        textArea.setBounds(400, 200, 250, 200);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(400, 200, 250, 200);
        frame.add(scrollPane);
        
        JLabel labelRisultato = new JLabel("RISULTATO:");
        frame.add(labelRisultato);
        labelRisultato.setBounds(400, 150, 300, 50);
        


        calcola.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            String funzione = jtxt1.getText();  // PRENDO LA FUNZIONE
            double a = Double.parseDouble(jtxt2.getText());
            double b = Double.parseDouble(jtxt3.getText());
            int rettangoli = Integer.parseInt(jtxt4.getText());

            double s = 0;
            double S = 0;
            double deltaS;

            Expression expr = new ExpressionBuilder(funzione)
                    .variables("x")
                    .build();

            double lunghezzaRettangoli = (b - a) / (double) rettangoli;

            for (int i = 0; i < rettangoli; i++) {
                double xi = a + i * lunghezzaRettangoli;
                double fxi = expr.setVariable("x", xi).evaluate();
                s += fxi * lunghezzaRettangoli;
            }

            for (int i = 1; i <= rettangoli; i++) {
                double xi = a + i * lunghezzaRettangoli;
                double fxi = expr.setVariable("x", xi).evaluate();
                S += fxi * lunghezzaRettangoli;
            }

            deltaS = S - s;

            textArea.setText("");
            textArea.append("s: " + s);
            textArea.append("\nS: " + S);
            textArea.append("\nDelta S: " + deltaS);

        } catch (NumberFormatException ex) {
            textArea.setText("Errore: Inserisci solo numeri validi per A, B e Rettangoli!");
        } catch (IllegalArgumentException ex) {
            textArea.setText("Errore: La funzione inserita non è valida!");
        }
    }
});

    }
}
