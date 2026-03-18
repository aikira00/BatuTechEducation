package Jvth04.dado;

import java.util.Random;

public class Es3DadoThread extends Thread{

    private static final int LIMIT = 50;
    private int score;
    private int numberOfrolls;
    Random r;

    public Es3DadoThread(String name){
        super();
        this.setName(name);
        r = new Random();
        this.score = 0;
        this.numberOfrolls = 0;
    }
    public void run(){
        System.out.println(getName() + " inizia a lanciare il dado!");

        while(score < LIMIT){
            int dice = r.nextInt(6) + 1;
            score += dice;
            numberOfrolls++;
            System.out.println(getName() + " rolls " + dice
                    + " → score: " + score);
            try {
                Thread.sleep(100); // 100 millisecondi di pausa tra i lanci del dado
            } catch (InterruptedException e) {
                System.out.println("Thread uscito dallo sleep per interruzione");
            }
        }

        System.out.println(getName() + " reached " + score
                + " in " + numberOfrolls + " rolls!");
    }

    public int getScore() {
        return score;
    }

    public int getRolls() {
        return numberOfrolls;
    }
}
