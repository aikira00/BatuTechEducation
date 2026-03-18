package ESERCIZI.pingPong;

public class ThreadPing extends Thread{
    PingPong pingpong;
    public boolean stop;

    public ThreadPing(PingPong pingpong){
        this.pingpong = pingpong;
        stop = false;
    }


    public void run(){
        while(!stop){
            pingpong.ping();
        }
    }

    public void ferma(){
        this.stop = true;
    }

}
