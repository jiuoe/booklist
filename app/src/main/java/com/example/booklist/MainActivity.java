package com.example.booklist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity{
    @SuppressLint("NotifyDataSetChanged")
    RecyclerView mRecyclerView;
    static MyAdapter mMyAdapter;
    Button add;
    static List<Book> mNewsList = new ArrayList<>();
    public static final int delete = 0;
    public static final int update = 1;
    public static final int New = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);



        Objects.requireNonNull(getSupportActionBar()).hide();
        add = (Button)findViewById(R.id.button_add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Edit_activity.class);
                startActivity(intent);
            }

        });

        Book book1 = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2,"xx");
        Book book2 = new Book("创新工程实践", R.drawable.book_no_name,"xx");
        Book book3 = new Book("信息安全数学基础（第2版）", R.drawable.book_1,"xx");
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
    @SuppressLint("NotifyDataSetChanged")
    //  添加数据
    public static void addData(Book book) {
//      在list中添加数据，并通知条目加入一条
        mNewsList.add(book);


    }
    @SuppressLint("NotifyDataSetChanged")
    ///contextmenu选项
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position = mMyAdapter.getContextMenuPosition();
        if (item.getItemId() == delete) {
            mNewsList.remove(position);
            mMyAdapter.notifyDataSetChanged();
        }
        else if (item.getItemId() == update) {
            book_edit.get_position(position);
            System.out.println(position);
            Intent intent=new Intent(MainActivity.this,book_edit.class);
            startActivity(intent);

        }
        return super.onContextItemSelected(item);
    }


    public static void book_edit(String str,int position) {
        mNewsList.get(position).book_title_edit(str);
    }
    ///适配器
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoder> {
        private int position;
        public int getContextMenuPosition() {return position; }
        public void setContextMenuPosition(int position) { this.position = position; }
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
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("menu");
                contextMenu.add(ContextMenu.NONE,0,ContextMenu.NONE,"删除");
                contextMenu.add(ContextMenu.NONE,1,ContextMenu.NONE,"修改");
            }
        }
    }


}




