package com.example.booklist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    List<Book> mNewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        Book book1 = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2);
        Book book2 = new Book("创新工程实践", R.drawable.book_no_name);
        Book book3 = new Book("信息安全数学基础（第2版）", R.drawable.book_1);
        // 构造一些数据

        mNewsList.add(book1);

        mNewsList.add(book2);

        mNewsList.add(book3);

        mMyAdapter = new MyAdapter();

        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDivider);

    }
    public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoder> {

        @NonNull
        @Override

        public MyAdapter.ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            //传入一个item布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);

            return new ViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHoder holder, int position) {
            Book book=mNewsList.get(position);
            holder.textView.setText(book.getTitle());
            holder.imageViewImage.setImageResource(book.getCoverResourceId());

        }

        @Override
        public int getItemCount() {
            //需要展示的个数
            return mNewsList.size();
        }
        //需要定义一个内部类继承ViewHolder
         class ViewHoder extends RecyclerView.ViewHolder{
            private final TextView textView;
            private final ImageView imageViewImage;
            public ViewHoder(@NonNull View itemView) {
                super(itemView);

                textView= itemView.findViewById(R.id.text_view_book_title);
                imageViewImage=itemView.findViewById(R.id.image_view_book);
            }

        }
    }


}




