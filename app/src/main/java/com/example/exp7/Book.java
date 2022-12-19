package com.example.exp7;


import java.io.Serializable;

public class Book implements Serializable {
    int imageView;
    String name;
    String writer;
    private static final long serialVersionUID = 3050851876068541840L;

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
