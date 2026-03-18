public class Es2SevenRunnable implements Runnable{

    String name;
    public Es2SevenRunnable(String name){
       this.name = name;
    }
    public void run(){
        for(int i =0; i<7; i++){
            System.out.println((i+1) + " " + name);
        }
    }
}