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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    public static final int delete = 0;
    public static final int update = 1;
    public static final int New = 2;
    static ArrayList<Book> mNewsList=new ArrayList<Book>() ;
    RecyclerView mRecyclerView;
    static MyAdapter mMyAdapter;

    //private  static  File f = new File("D:\\Data.text");
    //private  static final File  file=new File(Environment.getExternalStorageDirectory().getPath() + "/Book.txt");
    Book book1 = new Book("软件项目管理案例教程（第4版）", R.drawable.book_2, "xx");
    Book book2 = new Book("创新工程实践", R.drawable.book_no_name, "xx");
    Book book3 = new Book("信息安全数学基础（第2版）", R.drawable.book_1, "xx");

/*   static {
       try {
           if(file.exists())
               System.out.println("文件已存在");
           else{
               System.out.println("文件待创建");
               file.createNewFile();
               System.out.println("文件已创建");
           }
       } catch (IOException ex) {
           System.out.println("文件出错");
           ex.printStackTrace();
       }
   }*/
    // 构造一些数据

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewsList=(new Datasaver().Load(MainActivity.this.getBaseContext()));
        System.out.println(mNewsList);
        mRecyclerView = findViewById(R.id.recyclerview);
        //Objects.requireNonNull(getSupportActionBar()).hide();
       if(mNewsList.size()==0){
            mNewsList.add(book1);
            mNewsList.add(book2);
            mNewsList.add(book3);
       }

        /*BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            String readLine = "";
            int count =0;
            while((readLine = br.readLine()) != null){
                String[] list =readLine.split("-");
                if(list[0].equals(""))
                {
                    continue;
                }
                System.out.println(list[0]);
                int x =Integer.parseInt(list[1]);
                Book book=new Book(list[0],x,"xx");
                mNewsList.add(book);
                count++;
                if(count==10)
                {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
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
            System.out.println(position);
            mMyAdapter.delete(position);

            mRecyclerView.setAdapter(mMyAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            new Datasaver().Save(MainActivity.this.getBaseContext(),mNewsList);

          /*  mNewsList.remove(position);
            mMyAdapter.notifyDataSetChanged();*/


        }
        if (item.getItemId() == update) {
            get_position(position);
            Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
            startActivity(intent);


        }
        if (item.getItemId() == New) {
            Book book = new Book("xx", R.drawable.book_no_name, "xx");
            mNewsList.add(book);
            new Datasaver().Save(MainActivity.this.getBaseContext(),mNewsList);
            mMyAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }


    public static void book_edit(String str, int position) {
        mNewsList.get(position).book_title_edit(str);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoder> {

        private int position;

        public int getContextMenuPosition() {
            return position;
        }

        public void setContextMenuPosition(int position) {
            this.position = position;
        }

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

            private final TextView textView;
            private final TextView textView_writer;
            private final ImageView imageViewImage;

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
/*    public  void save() {
            //强制异常处理
            try {
                //第一步：
                // 定义File对象，定义目标源。
                // 内存数据写成文件，通常是不允许直接打开预览的
                // 大部分应用程序是写后缀，
                // 游戏类的会自定义名称，
                // 标准开发的时候，推荐:  .es
                //第二步：创建 基础 字节 输出流
                System.out.println("1");
                FileOutputStream fos = new FileOutputStream(f);
                System.out.println("2");
                //第三步：
                // 创建高层 对象 输出 流
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                System.out.println("3");
                //第四步：写文件，生成序列化文档
                oos.writeObject(mNewsList);
                System.out.println("4");
                //关闭
                oos.close();
                fos.close();
                //提示：
                System.out.println("保存成功！");
                System.out.println(mNewsList);
            }
            catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("保存失败！");
            }
        }
    public  void load() {
            try {
                //【反序列化】：把物理文件，恢复到内存
                //第一步：（目标源）
                //第二步：基础 字节 输入流
                System.out.println("1");
                FileInputStream fis = new FileInputStream(f);
                //第三步：高层对象输入流
                System.out.println("2");
                ObjectInputStream ois = new ObjectInputStream(fis);
                //第四步：
                // 调用方法，读取文件，得到一个Object对象
                // 文件读取到系统中，都是一个Object
                //第五步：
                //转换类型，把对象转换成所对应的类
                //这时，会判断： serialVersionUID 是否一样
                System.out.println("3");
                mNewsList = (ArrayList<Book>)ois.readObject();
                System.out.println("4");
                //关闭
                ois.close();
                fis.close();
                System.out.println("读取成功！");
                System.out.println(mNewsList);
            }
            catch(Exception ex) {
                System.out.println("读取失败！");
                ex.printStackTrace();

            }
        }*/
}
