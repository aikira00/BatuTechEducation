package elfi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blinky extends Thread {
    private listaRegalo listaBabbo;
    private List<String> speditoList = new ArrayList<>();
    private String nome;
    private int nBlinky;
    private int BlinkyC = 0;
    
    public Blinky(listaRegalo listaBabbo, String nome, int nBlinky) {
        this.listaBabbo = listaBabbo;
        this.nome = nome;
        this.nBlinky = nBlinky;
        this.setName(nome);
    }

    public void run(){
        while(!listaBabbo.spedisci(this).equals("stop"));
        
        System.out.println(Thread.currentThread().getName() + " ha finito.\n");
    }

    public List<String> getSpeditoList() {
        return speditoList;
    }
        public String getNome() {
        return nome;
    }

    public listaRegalo getListaBabbo() {
        return listaBabbo;
    }

    public int getnBlinky() {
        return nBlinky;
    }

    public void setListaBabbo(listaRegalo listaBabbo) {
        this.listaBabbo = listaBabbo;
    }

    public void setSpeditoList(List<String> speditoList) {
        this.speditoList = speditoList;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setnBlinky(int nBlinky) {
        this.nBlinky = nBlinky;
    }
    
    public void incrementC(){
        BlinkyC++;
    }

    public int getBlinkyC() {
        return BlinkyC;
    }

    public void setBlinkyC(int BlinkyC) {
        this.BlinkyC = BlinkyC;
    }
    
}
