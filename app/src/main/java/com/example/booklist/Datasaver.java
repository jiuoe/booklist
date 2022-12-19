package com.example.booklist;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Datasaver {
    public  void Save(Context context, ArrayList<Book> data)
    {
        try {

            FileOutputStream dataStream=context.openFileOutput("book_list.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(dataStream);
            out.writeObject(data);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    public ArrayList<Book> Load(Context context)
    {
        ArrayList<Book> data=new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("book_list.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (ArrayList<Book>) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
