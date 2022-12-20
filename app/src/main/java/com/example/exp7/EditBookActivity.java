package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;


public class EditBookActivity extends AppCompatActivity {
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
        setContentView(R.layout.edit_book);


        Objects.requireNonNull(getSupportActionBar()).hide();

        book=FragmentOne.mNewsList.get(position);
        init();
        edit.setText(book.getTitle());


        no.setOnClickListener(v -> finish());
        yes.setOnClickListener(v -> {
            title=edit.getText().toString();
            FragmentOne.book_edit(title, position);

            new Datasaver().Save(EditBookActivity.this.getBaseContext(),FragmentOne .mNewsList);
            finish();

        });
    }

    public static void get_position(int pos){
        position=pos;
    }
    public void init(){
        edit=findViewById(R.id.book_edit);
        yes=findViewById(R.id.button_yes);
        no=findViewById(R.id.button_no);}
}