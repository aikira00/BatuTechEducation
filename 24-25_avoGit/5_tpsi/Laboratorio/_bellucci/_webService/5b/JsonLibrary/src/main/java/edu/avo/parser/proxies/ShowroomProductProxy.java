/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.parser.proxies;

import edu.avo.parser.proxies.ShowroomProxy;
import edu.avo.parser.proxies.ProductProxy;

/**
 *
 * @author palma
 */
public class ShowroomProductProxy {
    private ShowroomProxy showroom;
    private ProductProxy product;

    public ShowroomProxy getShowroom() {
        return showroom;
    }

    public void setShowroom(ShowroomProxy showroom) {
        this.showroom = showroom;
    }

    public ProductProxy getProduct() {
        return product;
    }

    public void setProduct(ProductProxy product) {
        this.product = product;
    }
    
    
}
