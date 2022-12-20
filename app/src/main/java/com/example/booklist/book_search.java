package com.example.booklist;



import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;



public class book_search extends AppCompatActivity {

    int i=MainActivity.mNewsList.size();
    EditText text;
    Button yes;
    ImageButton back;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        String mSearch[] = new String[i];
        String mSearch_2[]=new String[i];
        getSupportActionBar().hide();
        for(int i=0;i<MainActivity.mNewsList.size();i++){
            mSearch[i]= MainActivity.mNewsList.get(i).getTitle();
          /*  mSearch_2[i]= MainActivity.mNewsList.get(i).getTitle();*/

        }
        //复现

        text = findViewById(R.id.search_text);
        back = findViewById(R.id.search_back);
        back.setOnClickListener(v -> finish());
        yes = findViewById(R.id.button2);


        listView=(ListView) findViewById(R.id.List_view);

        //3、准备数据
        // 4、创建适配器 连接数据源和控件的桥梁
        //参数 1：当前的上下文环境
        //参数 2：当前列表项所加载的布局文件
        //(android.R.layout.simple_list_item_1)这里的布局文件是Android内置的，里面只有一个textview控件用来显示简单的文本内容
        //参数 3：数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<>(book_search.this,android.R.layout.simple_list_item_1,mSearch);
        //5、将适配器加载到控件中
        listView.setAdapter(adapter);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=text.getText().toString();
                int j=0; System.out.println(str+"str");
                for (int i = 0; i < mSearch.length; i++) {
                    if (mSearch[i].contains(str)) {//a是b的子集}或者
                        System.out.println(j+mSearch[i]);
                    }else{
                        mSearch[i]=" ";
                    }
                }
                adapter.notifyDataSetChanged();
            }

        });
        //6、为列表中选中的项添加单击响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String result=((TextView)view).getText().toString();
                Toast.makeText(book_search.this,"您选择的书籍是："+result,Toast.LENGTH_LONG).show();

            }
        });

    }
}

