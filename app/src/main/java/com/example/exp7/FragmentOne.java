/*package com.example.exp7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container);

        return view;
    }
}*/
package com.example.exp7;

import static com.example.exp7.EditBookActivity.get_position;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;



public class FragmentOne extends Fragment {
    public static final int delete = 0;
    public static final int update = 1;
    public static final int New = 2;
    static ArrayList<Book> mNewsList=new ArrayList<>() ;
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    Book book1 = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2, "xx");
    Book book2 = new Book("创新工程实践", R.drawable.book_no_name, "xx");
    Book book3 = new Book("信息安全数学基础（第2版）", R.drawable.book_1, "xx");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Datasaver().Load(FragmentOne.this.getContext());
        //Objects.requireNonNull(getSupportActionBar()).hide();
        if(mNewsList.size()==0){
            mNewsList.add(book1);
            mNewsList.add(book2);
            mNewsList.add(book3);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container);
        mRecyclerView =  view.findViewById(R.id.recyclerview);
        registerForContextMenu(mRecyclerView);
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position = mMyAdapter.getContextMenuPosition();
        if (item.getItemId() == delete) {
            System.out.println(position);
            mMyAdapter.delete(position);

            mRecyclerView.setAdapter(mMyAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(layoutManager);

            /*mNewsList.remove(position);*/
            mMyAdapter.notifyDataSetChanged();
            new Datasaver().Save(FragmentOne.this.getContext(),mNewsList);

        }
        if (item.getItemId() == update) {
            get_position(position);
            Intent intent = new Intent(this.getContext(), EditBookActivity.class);
            startActivity(intent);


        }
        if (item.getItemId() == New) {
            Book book = new Book("xx", R.drawable.book_no_name, "xx");
            mNewsList.add(book);
            new Datasaver().Save(FragmentOne.this.getContext(),mNewsList);
            mMyAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }


    public static void book_edit(String str, int position) {
        mNewsList.get(position).book_title_edit(str);
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoder> {

        private int position;

        public int getContextMenuPosition() {
            return position;
        }

        public void setContextMenuPosition(int position) {
            this.position = position;
        }

        @NonNull
        @Override

        public MyAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //传入一个item布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            /*  View view = View.inflate(MainActivity.this, R.layout.item_list, null);*/
            return new MyAdapter.ViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
            Book book = mNewsList.get(position);
            holder.textView.setText(book.getTitle());
            holder.imageViewImage.setImageResource(book.getCoverResourceId());
            holder.textView_writer.setText(book.getWriter());
            holder.itemView.setOnLongClickListener(v -> {
                setContextMenuPosition(holder.getLayoutPosition());
                return false;
            });
        }

        public void delete(int position)
        {
            mNewsList.remove(position);
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

             TextView textView;
             TextView textView_writer;
             ImageView imageViewImage;

            public ViewHoder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.text_view_book_title);
                imageViewImage = itemView.findViewById(R.id.image_view_book);
                textView_writer = itemView.findViewById(R.id.textView_writer);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("menu");
                menu.add(ContextMenu.NONE, 0, ContextMenu.NONE, "删除");
                menu.add(ContextMenu.NONE, 1, ContextMenu.NONE, "修改");
                menu.add(ContextMenu.NONE, 2, ContextMenu.NONE, "新建");
            }
        }
    }
}
