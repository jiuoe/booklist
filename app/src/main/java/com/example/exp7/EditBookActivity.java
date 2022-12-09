package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity {
    EditText edit;
    Button yes;
    Button no;
    Book book;
    String title;
    static int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);


        getSupportActionBar().hide();

        book=MainActivity.mNewsList.get(position);
        edit=findViewById(R.id.book_edit);
        edit.setText(book.getTitle());


        yes=findViewById(R.id.button_yes);
        no=findViewById(R.id.button_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                title=edit.getText().toString();
                MainActivity.book_edit(title, position);
                MainActivity.mMyAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }
    public static void get_position(int pos){
        position=pos;
    }
}