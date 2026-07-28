/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.parser;

import edu.avo.bolibrary.Category;
import edu.avo.bolibrary.Product;
import edu.avo.bolibrary.Showroom;
import edu.avo.bolibrary.ShowroomProduct;
import edu.avo.parser.proxies.ShowroomProductProxy;
import edu.avo.parser.proxies.ShowroomProxy;
import edu.avo.parser.proxies.ProductProxy;
import edu.avo.parser.proxies.CategoryProxy;


/**
 *
 * @author palma
 */
public class ProxyObjectConverter {

    public static CategoryProxy getCategoryProxy(Category category) {
        CategoryProxy proxy = new CategoryProxy();
        proxy.setId(category.getId());
        proxy.setDescription(category.getDescription());
        return proxy;
    }

    public static Category getCategory(CategoryProxy proxy) {
        return new Category(proxy.getId(), proxy.getDescription());
    }
    
    public static ProductProxy getProductProxy(Product product){
        ProductProxy proxy=new ProductProxy();
        proxy.setId(product.getId());
        proxy.setName(product.getName());
        proxy.setDescription(product.getDescription());
        proxy.setPrice(product.getPrice());
        CategoryProxy cProxy=getCategoryProxy(product.getCategory());
        proxy.setCategory(cProxy);
        return proxy;
    }
    
    public static Product getProduct(ProductProxy proxy) {
        return new Product(proxy.getId(), proxy.getName(),proxy.getDescription(),
                            proxy.getPrice(),getCategory(proxy.getCategory()));
    }
    
    public static ShowroomProxy getShowroomProxy(Showroom showroom){
        ShowroomProxy proxy=new ShowroomProxy();
        proxy.setId(showroom.getId());
        proxy.setName(showroom.getName());
        proxy.setAddress(showroom.getAddress());
        proxy.setCity(showroom.getCity());
        proxy.setManager(showroom.getManager());
        return proxy;
    }
    
    public static Showroom getShowroom(ShowroomProxy proxy) {
        return new Showroom(proxy.getId(), proxy.getName(),proxy.getAddress(),
                            proxy.getCity(),proxy.getManager());
    }
    
    public static ShowroomProductProxy getShowroomProductProxy(ShowroomProduct sp){
        ShowroomProductProxy proxy=new ShowroomProductProxy();
        proxy.setProduct(getProductProxy(sp.getProduct()));
        proxy.setShowroom(getShowroomProxy(sp.getShowroom()));
        return proxy;
    }
    
    public static ShowroomProduct getShowroomProduct(ShowroomProductProxy proxy){
        return new ShowroomProduct(getShowroom(proxy.getShowroom()), getProduct(proxy.getProduct()));
    }
}
