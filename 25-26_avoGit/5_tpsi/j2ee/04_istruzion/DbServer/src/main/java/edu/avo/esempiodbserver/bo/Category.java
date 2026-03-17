/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.esempiodbserver.bo;

/**
 *
 * @author MULTI01
 */
public class Category {
    private int id;
    private String description;

    //Da usare quando si estrae dal database
    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    // Da usare quando si crea una nuova categoria
    public Category(String description) {
        this.description = description;
        id=-1;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
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
        final Category other = (Category) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", description=" + description + '}';
    }
    
    
    
} 
    
    
    
   
