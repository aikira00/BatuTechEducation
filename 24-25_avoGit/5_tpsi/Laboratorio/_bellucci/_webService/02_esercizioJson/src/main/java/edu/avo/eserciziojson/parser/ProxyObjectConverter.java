/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziojson.parser;

import edu.avo.eserciziojson.bo.Category;

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
}
