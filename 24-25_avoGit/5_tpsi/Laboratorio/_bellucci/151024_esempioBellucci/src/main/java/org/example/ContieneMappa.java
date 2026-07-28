package org.example;

import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class ContieneMappa {
    Map<String, String> map;
    String id;

    // this should be String id and SPM spm: port@address, spm
    public ContieneMappa(Map<String, String> map, String id) {
        this.map = map;
        map.put(id, "Sono il client con " + id );
    }

    public void put(String id) {
        map.put(id, "Sono il client numero " + id);
    }

    public void stampaMappa(){

        Set<String> keys = map.keySet();
        for(String key : keys){
            System.out.println(key + ": " + map.get(key));
        }
    }

    // return value shoud return SPM
    public String getMap(String id) {
        return map.get(id);
    }




}