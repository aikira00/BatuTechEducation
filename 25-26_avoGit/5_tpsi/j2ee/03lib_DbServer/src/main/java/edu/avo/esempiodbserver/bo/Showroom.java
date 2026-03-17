/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.esempiodbserver.bo;

/**
 *
 * @author MULTI01
 */
public class Showroom {
    private int id;
    private String name;
    private String address;
    private String city;
    private String manager;

    public Showroom(int id, String name, String address, String city, String manager) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.manager = manager;
    }

    public Showroom(String name, String address, String city, String manager) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Showroom other = (Showroom) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Showroom{" + "id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", manager=" + manager + '}';
    }
    
    
    
}
