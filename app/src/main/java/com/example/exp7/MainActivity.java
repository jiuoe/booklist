package com.example.exp7;

import static com.example.exp7.EditBookActivity.get_position;

import androidx.fragment.app.Fragment;
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
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPage;
    private String[] tabTitles;//tab的标题
    private List<Fragment> mDatas = new ArrayList<>();//ViewPage2的Fragment容器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPage = findViewById(R.id.view_page);

        MyViewPageAdapter mAdapter = new MyViewPageAdapter(this, mDatas);
        mViewPage.setAdapter(mAdapter);

        //TabLayout与ViewPage2联动关键代码
        new TabLayoutMediator(mTabLayout, mViewPage, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        }).attach();
        mViewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        //TabLayout的选中改变监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void initData() {
        tabTitles = new String[]{"图书", "新闻", "卖家"};
        FragmentOne frgOne = new FragmentOne();
        FragmentTwo frgTwo = new FragmentTwo();
        FragmentThree frgThree = new FragmentThree();
        mDatas.add(frgOne);
        mDatas.add(frgTwo);
        mDatas.add(frgThree);
    }
}

