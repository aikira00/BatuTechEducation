package ESERCIZI.caramelle;

    class MagicCandyFactory {
        private int candies = 0;
        private final int capacity = 10;

        public synchronized void deposita(int n) {
            while (candies == capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            //il distributore ha 7 caramelle, e ne può contenere ancora 3
            //il produttore vuole produrne 4
            //min(10-7, 4) e quindi ne produce 3
            int producedCandies = Math.min(capacity - candies, n);
            candies += producedCandies;
            System.out.println(Thread.currentThread().getName() + ": Depositate " + producedCandies + " caramelle. Totale: " + candies);
            notifyAll();
        }

        public synchronized void preleva(int n) {
           // il distributore è vuoto
            while (candies == 0 ) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            //il consumatore vuole 3 caramelle, il distributore ne ha 2 min(2,3) => consuma 2
            //il consumatore vuole 5 caramelle, il distributore ne ha 7 => min(7, 5) => consuma 5
            int consumedCandies = Math.min(candies, n);
            candies -= consumedCandies;
            System.out.println(Thread.currentThread().getName() + ": consumate " +  consumedCandies + " caramelle. Rimaste: " + candies);
            notifyAll();
        }

        public int getValue(){
            return candies;
        }
    }

