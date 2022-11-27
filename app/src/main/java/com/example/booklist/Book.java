package com.example.booklist;
import java.util.ArrayList;

public class Book {
    int imageView;
    String name;

    public Book(String s, int book_2) {
        this.name = s;
        this.imageView=book_2;
    }

    public int getCoverResourceId() {
        return imageView;
    }

    public String getTitle() {
        return name;
    }
    ArrayList<Book> getListBook(ArrayList<Book> mNewsList){
        return mNewsList;
    }
}
