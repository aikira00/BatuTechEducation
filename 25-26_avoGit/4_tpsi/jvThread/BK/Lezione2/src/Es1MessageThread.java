/*La classe MessageLoop rappresenta il thread secondario.
 Stampa una serie di messaggi e attende per 2 secondi tra
  ciascun messaggio. Se viene interrotto prima del completamento,
  stampa un messaggio appropriato.
 */
public class Es1MessageThread implements Runnable{

    public void threadMessage(String msg){
        String threadName =
                Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, msg);
    }
    public void run() {
        String importantInfo[] = {
                "Uno sguardo che rivela",
                "il tormento interiore",
                "aggiunge bellezza al volto,",
                "per quanta tragedia e pena riveli,",
                "mentre il volto che non esprime, nel silenzio,",
                "misteri nascosti non è bello,",
                "nonostante la simmetria dei lineamenti.",
                "Il calice non attrae le labbra",
                "se non traluce il colore del vino",
                "attraverso la trasparenza del cristallo."
        };
        try {
            for(int i = 0; i < importantInfo.length; i++) {
                // Pause for 4 seconds
                Thread.sleep(4000);
                // Print a message
                threadMessage(importantInfo[i]);
            }
        } catch (InterruptedException e) {
            threadMessage("Non avevo finito!");
        }
    }
}
