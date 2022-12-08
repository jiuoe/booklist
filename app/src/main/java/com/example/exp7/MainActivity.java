package com.example.exp7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final int delete = 0;
    public static final int update = 1;
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    Book book1 = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2,"xx");
    Book book2 = new Book("创新工程实践", R.drawable.book_no_name,"xx");
    Book book3 = new Book("信息安全数学基础（第2版）", R.drawable.book_1,"xx");
    // 构造一些数据
    List<Book> mNewsList = new ArrayList<Book>() {{
        add(book1);

        add(book2);

        add(book3);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);



        //Objects.requireNonNull(getSupportActionBar()).hide();



        mMyAdapter = new MyAdapter();



        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        registerForContextMenu(mRecyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDivider);

    }

    @SuppressLint("NotifyDataSetChanged")

    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position = mMyAdapter.getContextMenuPosition();
        if (item.getItemId() == delete) {
         /*   System.out.println(position);
            mMyAdapter.delete(position);

            mRecyclerView.setAdapter(mMyAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);*/
            System.out.println(position);
            mNewsList.remove(position);

            mMyAdapter.notifyDataSetChanged();
        } else if (item.getItemId() == update) {

        }
        return super.onContextItemSelected(item);
    }

    public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoder> {

        private int position;
        public int getContextMenuPosition() {return position; }
        public void setContextMenuPosition(int position) { this.position = position; }

        @NonNull
        @Override

        public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //传入一个item布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
          /*  View view = View.inflate(MainActivity.this, R.layout.item_list, null);*/
            return new ViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
            Book book=mNewsList.get(position);
            holder.textView.setText(book.getTitle());
            holder.imageViewImage.setImageResource(book.getCoverResourceId());
            holder.textView_writer.setText(book.getWriter());
            holder.itemView.setOnLongClickListener(v -> {
                setContextMenuPosition(holder.getLayoutPosition());
                return false;
            });
        }
        public void onViewRecycled(MyAdapter.ViewHoder holder) {
            holder.itemView.setOnLongClickListener(null);
            super.onViewRecycled(holder);
        }

        public void delete(int position)
        {

            mNewsList.remove(position);
        }

        @Override
        public int getItemCount() {
            //需要展示的个数
            return mNewsList.size();
        }
        //需要定义一个内部类继承ViewHolder
        class ViewHoder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

            private final TextView textView;
            private final TextView textView_writer;
            private final ImageView imageViewImage;
            public ViewHoder(@NonNull View itemView) {
                super(itemView);

                textView= itemView.findViewById(R.id.text_view_book_title);
                imageViewImage=itemView.findViewById(R.id.image_view_book);
                textView_writer=itemView.findViewById(R.id.textVie_writer);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("menu");
                menu.add(ContextMenu.NONE,0,ContextMenu.NONE,"删除");
                menu.add(ContextMenu.NONE,1,ContextMenu.NONE,"修改");
            }
        }
    }
    }

