/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avo.bolibrary;



/**
 *
 * @author palma
 */
public class Showroom {
    private int id = -1;
    private String name;
    private String address;
    private String city;
    private String manager;

    public Showroom(int id, String name, String address, String city, String manager) {
        this(name, address, city, manager);
        this.id = id;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Showroom other = (Showroom) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Showroom{" + "id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", manager=" + manager + '}';
    }

    
}
