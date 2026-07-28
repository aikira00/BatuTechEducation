package edu.avo.exampleJsonBJsonP;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(){

    }

    public  Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
