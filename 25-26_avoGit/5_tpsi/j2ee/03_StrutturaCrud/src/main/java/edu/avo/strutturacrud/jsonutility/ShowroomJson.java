package edu.avo.strutturacrud.jsonutility;

import edu.avo.esempiodbserver.bo.Showroom;


public class ShowroomJson {
    private int id = -1;
    private String name;
    private String address;
    private String city;
    private String manager;

    public ShowroomJson() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public Showroom getShowroom(){
        Showroom s;
        if(id<0){
            s = new Showroom(name,address,city,manager);
        }else{
           s = new Showroom(id,name,address,city,manager); 
        }
        return s;
    }
}
