/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziojson.bo;

import edu.avo.eserciziojson.*;
import java.util.Objects;

/**
 *
 * @author palma
 */
public class ShowroomProduct {
    
    private Showroom showroom;
    private Product product;

    public ShowroomProduct(Showroom showroom, Product product) {
        this.showroom = showroom;
        this.product = product;
    }

    public Showroom getShowroom() {
        return showroom;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.showroom);
        hash = 47 * hash + Objects.hashCode(this.product);
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
        final ShowroomProduct other = (ShowroomProduct) obj;
        if (!Objects.equals(this.showroom, other.showroom)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "ShowroomProduct{" + "showroom=" + showroom + ", product=" + product + '}';
    }
    
    
}
