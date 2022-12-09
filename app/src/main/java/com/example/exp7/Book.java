package com.example.exp7;

public class Book {
    int imageView;
    String name;
    String writer;


    public Book(String s, int book_2,String w) {
        this.name = s;
        this.imageView=book_2;
        this.writer=w;
    }

    public int getCoverResourceId() {
        return imageView;
    }

    public String getTitle() {
        return name;
    }

    public String getWriter(){return writer;}
    public void book_title_edit(String tit){this.name=tit;}

}
