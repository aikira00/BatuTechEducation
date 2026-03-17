package edu.avo.strutturacrud.jsonutility;

import edu.avo.esempiodbserver.bo.Product;



public class ProductJson {
    private int id = -1;
    private String name;
    private String description;
    private float price;
    private CategoryJson category;

    public ProductJson() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(CategoryJson category) {
        this.category = category;
    }
    
    public Product getProduct(){
        Product p;
        if(id<0){
            p = new Product(name,description,price,category.getCategory());
        }else{
            p = new Product(id,name,description,price,category.getCategory());
        }
        return p;
    }
}
