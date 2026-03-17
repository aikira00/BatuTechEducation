package edu.avo.strutturacrud.jsonutility;

import edu.avo.esempiodbserver.bo.Category;

public class CategoryJson {

    private int id=-1;
    private String description;
    
    public CategoryJson() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Category getCategory(){
        Category c;
        if(id<0){
            c=new Category(description);
        } else{
            c=new Category(id, description);
        }
        return c;
    }
    
}
