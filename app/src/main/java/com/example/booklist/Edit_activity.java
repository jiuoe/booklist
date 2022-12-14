package com.example.booklist;

import static com.example.booklist.R.string.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Edit_activity extends AppCompatActivity {
    Book book;

    ImageButton back;
    Button yes;
    Button cancel;

    EditText book_title;
    EditText book_writer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //去掉标题栏
        getSupportActionBar().hide();

        back = findViewById(R.id.image_back_Button);
        back.setOnClickListener(v -> finish());
        yes=findViewById(R.id.update);
        book_title=findViewById(R.id.input_book_title);
        book_writer=findViewById(R.id.input_book_writer);
        yes.setOnClickListener(v -> {
            String name=book_title.getText().toString();
            String writer=book_writer.getText().toString();
            if(name.length()==0||writer.length()==0){
                Toast.makeText(Edit_activity.this, getString(输入提示), Toast.LENGTH_LONG).show();
                finish();
            }
            if(name.length()!=0&&writer.length()!=0) {
                int imageView = R.drawable.book;
                book = new Book(name, imageView, writer);
                MainActivity.addData(book);
                new Datasaver().Save(Edit_activity.this.getBaseContext(),MainActivity.mNewsList);
                finish();
            }

        });
        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> finish());
    }

}