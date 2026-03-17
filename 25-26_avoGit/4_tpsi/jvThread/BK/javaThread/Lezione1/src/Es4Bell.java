public class Es4Bell implements Runnable{
    private String sound;
    private int times;

    public Es4Bell(String sound, int times){
        this.sound = sound;
        this.times = times;
    }

    public void run(){
        for(int i =0; i< times; i++){
            System.out.println((i+1) + " " + sound );
        }
    }
}
