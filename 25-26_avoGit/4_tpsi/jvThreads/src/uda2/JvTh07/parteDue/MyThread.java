package JvTh07.parteDue;

class MyThread extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");

           /* cosa succede se il thead viene interrotto mentre dorme??? attenzione al flag
            try {
                Thread.sleep(500); // Simuliamo un'attività
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto!"); //così abbiamo shadow exception
                //come possiamo risolvere?
            }*/
        }
        System.out.println("Sono vivo: " + this.isAlive());

        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}