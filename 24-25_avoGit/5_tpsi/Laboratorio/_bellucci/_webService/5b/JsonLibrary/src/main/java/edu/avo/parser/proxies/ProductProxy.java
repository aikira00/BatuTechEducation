/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.parser.proxies;

import edu.avo.parser.proxies.CategoryProxy;

/**
 *
 * @author palma
 */
public class ProductProxy {
    private int id;
    private String name;
    private String description;
    private float price;
    private CategoryProxy category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public CategoryProxy getCategory() {
        return category;
    }

    public void setCategory(CategoryProxy category) {
        this.category = category;
    }
    
    
}
