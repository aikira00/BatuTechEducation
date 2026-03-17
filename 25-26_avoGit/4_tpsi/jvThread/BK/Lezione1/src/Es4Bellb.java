public class Es4Bellb implements Runnable{
    private String sound;
    private int times;

    public Es4Bellb(String sound, int times){
        this.sound = sound;
        this.times = times;
    }

    public void run(){
        try {
            for (int i = 0; i < times; i++) {
                System.out.println((i + 1) + " " + sound);
                Thread.sleep(2000); // 1000 ms = 1 second
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
