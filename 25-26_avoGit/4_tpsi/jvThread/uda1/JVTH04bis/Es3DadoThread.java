import java.util.Random;

public class Es3DadoThread extends Thread{

    private static final int limit = 1000;


    public Es3DadoThread(String name){
        super();
        this.setName(name);
    }
    public void run(){
        int counter = 0;
        Random r = new Random();
        while(counter<limit){
            int randomNumber = r.nextInt(6) + 1;
            //counter += randomNumber;
            if (counter + randomNumber <= limit) {
                counter += randomNumber;
                System.out.println(Thread.currentThread().getName() + " Tiro dado: " + randomNumber);
            }
            try {
                Thread.sleep(100); // 100 millisecondi di pausa tra i lanci del dado
            } catch (InterruptedException e) {
                System.out.println("Thread uscito dallo sleep per interruzione");
            }
        }
        System.out.println(Thread.currentThread().getName() + " sono arrivato a " + counter );
    }
}
