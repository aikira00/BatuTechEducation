package elfi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Twinky extends Thread {
    private listaRegalo listaBabbo;
    private List<String> prodottoList = new ArrayList<>();
    private String nome;
    private int nTwinky;
    private int TwinkyI = 0;
    
    public Twinky(listaRegalo listaBabbo, String nome, int nTwinky) {
        this.listaBabbo = listaBabbo;
        this.nome = nome;
        this.nTwinky = nTwinky;
        this.setName(nome);
    }

    public void run(){
        String prodotto = listaBabbo.produce(this);
        while(!prodotto.equals("stop")){
            prodotto = listaBabbo.produce(this);
            //qui agiiguno prodotto a prodottoList
        }
    }


    public List<String> getProdottoList() {
        return prodottoList;
    }

    public String getNome() {
        return nome;
    }

    public listaRegalo getListaBabbo() {
        return listaBabbo;
    }

    public void setListaBabbo(listaRegalo listaBabbo) {
        this.listaBabbo = listaBabbo;
    }

    public int getnTwinky() {
        return nTwinky;
    }

    public void setnTwinky(int nTwinky) {
        this.nTwinky = nTwinky;
    }

    public int getTwinkyI() {
        return TwinkyI;
    }

    public void setTwinkyI(int TwinkyI) {
        this.TwinkyI = TwinkyI;
    }
    
    public void incrementI(){
        TwinkyI++;
    }

    
    
}