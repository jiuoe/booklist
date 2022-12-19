package com.example.booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class book_edit extends AppCompatActivity {
    EditText edit;
    Button yes;
    Button no;
    Book book;
    String title;
    static int position;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);

        getSupportActionBar().hide();

        book=MainActivity.mNewsList.get(position);
        edit=findViewById(R.id.book_edit);
        edit.setText(book.getTitle());


        yes=findViewById(R.id.button_yes);
        no=findViewById(R.id.button_no);
        no.setOnClickListener(v -> finish());
        yes.setOnClickListener(v -> {
            title=edit.getText().toString();
            MainActivity.book_edit(title, position);
            MainActivity.mMyAdapter.notifyDataSetChanged();
            new Datasaver().Save(book_edit.this.getBaseContext(),MainActivity.mNewsList);
            finish();
        });
    }
    public static void get_position(int pos){
        position=pos;
    }
}

