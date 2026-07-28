package edu.avo.exampleJsonBJsonP;

public class BookWDescription {
    private String title;
    private String author;
    private int year;
    private String description;

    public BookWDescription(){

    }

    public  BookWDescription(String title, String author, int year, String description){
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    //cosa manca?
}
