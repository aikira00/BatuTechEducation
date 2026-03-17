public class NightOwlRace {
    public static void main(String[] args) {
        NightOwlThread gufo1 = new NightOwlThread();
        NightOwlThread gufo2 = new NightOwlThread();

        gufo1.start();
        gufo2.start();

        try {
            Thread.sleep(5000); // Attendiamo 5 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gufo1.stopRunning(); // Impostiamo running a false per il gufo1
        gufo2.stopRunning(); // Impostiamo running a false per il gufo2

        // Attendiamo che entrambi i thread terminino
        try {
            gufo1.join();
            gufo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Determiniamo il vincitore
        String vincitore;
        if (gufo1.getConteggioStelle() > gufo2.getConteggioStelle()) {
            vincitore = "Gufo 1";
        } else {
            vincitore = "Gufo 2";
        }
        System.out.println("Il vincitore è: " + vincitore);
    }

    static class NightOwlThread extends Thread {
        private volatile boolean running = true;
        private int conteggioStelle = 0;

        public void run() {
            while (running && !Thread.currentThread().isInterrupted()) {
                // Effettua il conteggio delle stelle durante il volo
                //conteggioStelle++;
                conteggioStelle += (int) (Math.random() * 100) + 1;
                try {
                    Thread.sleep(1000); // Riposo per un secondo
                } catch (InterruptedException e) {
                    // Gestione dell'interruzione
                    Thread.currentThread().interrupt(); // Reimposta il flag di interruzione
                }
            }
        }

        public int getConteggioStelle() {
            return conteggioStelle;
        }

        public void stopRunning() {
            running = false;
        }
    }
}
