package ESERCIZI.pingPong;

public class TestPingPong {
    public static void main(String[] args) {
        System.out.println("Ping-Pong 2 giocatori\n");

        PingPong pingPong = new PingPong();

        ThreadPing ping = new ThreadPing(pingPong);
        ThreadPong pong = new ThreadPong(pingPong);

        ping.start();
        pong.start();
        try{
            Thread.sleep(10000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " => sono stato interrotto!");
        }
        ping.ferma();
        pong.ferma();
        try{
            ping.join();
            pong.join();
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " => sono stato interrotto!");
        }
    }
}
