/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.client;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MULTI01
 */
public class Crivello extends Thread{
    private int min;
    private int max;
    private int [] list;
    private List<Integer> primi;

    public Crivello(int min, int max) {
        this.min = min;
        this.max = max;
        list=new int[max-min+1];
        for (int i = 0; i < list.length; i++) {
            list[i]=min++;
        }
        primi=new ArrayList<>();
    }

    public List<Integer> getPrimi() {
        return primi;
    }
    
    @Override
    public void run(){
        esegui();
    }
    
    private void esegui(){
        for (int i=0; i<list.length;i++){
            boolean ePrimo=true;
            for(int j=2;j<=Math.sqrt(list[i]) && ePrimo;j++){
                if(list[i]%j==0){
                    ePrimo=false;
                }
            }
            if(ePrimo){
                primi.add(list[i]);
            }
        }
    }
    
}
