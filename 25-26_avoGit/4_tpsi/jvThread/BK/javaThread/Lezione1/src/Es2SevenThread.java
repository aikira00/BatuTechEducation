public class Es2SevenThread extends Thread{

    public Es2SevenThread(String name){
        super();
        this.setName(name);
    }
    public void run(){
        for(int i =0; i<7; i++){
            System.out.println((i+1) + " " + this.getName());
        }
    }
}