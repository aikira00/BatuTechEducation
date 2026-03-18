package JvTh07.parteDue.sol;

class MyThread extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");

           /* cosa succede se il thead viene interrotto mentre dorme??? attenzione al flag*/
            try {
                Thread.sleep(500); // Simuliamo un'attività
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto!"); //così abbiamo shadow exception
                //come possiamo risolvere? aggiungiamo un return o un break
                break;
                //break; //se thread interrotto il thread, esco da while
                //return; //ancora meglio
            }
        }
        System.out.println("Sono vivo: " + this.isAlive());

        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}