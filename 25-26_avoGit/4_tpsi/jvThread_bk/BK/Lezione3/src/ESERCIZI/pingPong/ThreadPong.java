package ESERCIZI.pingPong;

public class ThreadPong extends Thread{
    PingPong pingpong;
    public boolean stop;

    public ThreadPong(PingPong pingpong){
        this.pingpong = pingpong;
        stop = false;
    }

    public void run(){
        while(!stop){
            pingpong.pong();
        }
    }

    public void ferma(){
        this.stop = true;
    }

}
