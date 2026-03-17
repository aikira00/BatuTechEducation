class Es3BellsJoin implements Runnable {
    static boolean dinSuonata = false;
    static boolean donSuonata = false;

    String suono;
    int volte;

    public Es3BellsJoin(String suono, int volte) {
        this.suono = suono;
        this.volte = volte;
    }

    public void run() {
        if ("din".equals(suono)) {
            while (donSuonata) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dinSuonata = true;
        } else if ("don".equals(suono)) {
            while (!dinSuonata) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            donSuonata = true;
        }

        for (int i = 0; i < volte; i++) {
            System.out.print(suono + " ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Es3BellsJoin cdin = new Es3BellsJoin("din", 5);
        Thread tdin = new Thread(cdin);
        Es3BellsJoin cdon = new Es3BellsJoin("don", 5);
        Thread tdon = new Thread(cdon);
        Es3BellsJoin cdan = new Es3BellsJoin("dan", 5);
        Thread tdan = new Thread(cdan);

        try {
            tdin.start();
            tdon.start();
            tdan.start();
            tdin.join();
            tdon.join();
            tdan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
