/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avo.battisti.essuudpbattisti;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Enrico
 */
public class CreatoreDiFile {
    public void creaFrammento(String nomeFile,String num,String testo) throws IOException{
        File file = new File(nomeFile+"_"+num);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        PrintWriter out = new PrintWriter(new FileWriter(nomeFile),true);
        out.print(testo);
        out.close();
    }
    public void ricostruzioneFile(){
        
    }
}
