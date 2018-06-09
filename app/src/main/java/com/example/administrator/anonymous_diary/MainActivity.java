package com.example.administrator.anonymous_diary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.diary.bean.DiaryBean;
import com.diary.utils.StatusBarCompat;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,IXListViewListener {

    private XListView mListView;
    private MyDiaryAdapter dAdapter;
    private ArrayList<DiaryBean> diarys = new ArrayList<DiaryBean>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private static int tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDiaryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        TextView textView = (TextView)headerLayout.findViewById(  R.id.textView2);
        textView.setText("user");

        geneDiarys(tag);
        mListView = (XListView) findViewById(me.maxwin.R.id.xListView);
        mListView.setPullLoadEnable(true);
        dAdapter = new MyDiaryAdapter(this, R.layout.diary_item, diarys);
        mListView.setAdapter(dAdapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
        mHandler = new Handler();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(tag == 4){
                    DiaryBean diaryBean = diarys.get(position - 1);
                    Intent intent = new Intent(MainActivity.this, EditDiaryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", diaryBean.getTitle());
                    bundle.putString("content", diaryBean.getContent());
                    bundle.putString("date", diaryBean.getDate());
                    intent.putExtras(bundle);

                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }else {
                    DiaryBean diaryBean = diarys.get(position - 1);
                    Intent intent = new Intent(MainActivity.this, BrowseDiaryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", diaryBean.getTitle());
                    bundle.putString("content", diaryBean.getContent());
                    bundle.putString("date", diaryBean.getDate());
                    intent.putExtras(bundle);

                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    //Toast.makeText(MainActivity.this, diaryBean.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        start = 0;
        diarys.clear();
        if(id == R.id.nav_discover) {
            tag = 0;
        } else if (id == R.id.nav_history) {
            tag = 1;
        } else if (id == R.id.nav_collection) {
            tag = 2;
        } else if (id == R.id.nav_exit) {
            tag = 3;
        } else if (id == R.id.nav_diary){
            tag = 4;
        }
        geneDiarys(tag);
        dAdapter = new MyDiaryAdapter(MainActivity.this, R.layout.diary_item, diarys);
        mListView.setAdapter(dAdapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void geneDiarys(int x) {
        if (tag == 0) {
            for (int i = 0; i != 20; ++i) {
                DiaryBean diary = new DiaryBean("2018年06月08日", "日记" + (++start), "xxxxx");
                diarys.add(diary);
            }
        }else if(tag == 1){
            for (int i = 0; i != 20; ++i) {
                DiaryBean diary = new DiaryBean("2018年06月08日", "历史日记" + (++start), "xxxxx");
                diarys.add(diary);
            }
        }else if(tag == 2){
            for (int i = 0; i != 20; ++i) {
                DiaryBean diary = new DiaryBean("2018年06月08日", "收藏日记" + (++start), "xxxxx");
                diarys.add(diary);
            }
        }else if(tag == 3){
            for (int i = 0; i != 20; ++i) {
                DiaryBean diary = new DiaryBean("2018年06月08日", "退出" + (++start), "xxxxx");
                diarys.add(diary);
            }
        }else if(tag == 4){
            for (int i = 0; i != 20; ++i) {
            DiaryBean diary = new DiaryBean("2018年06月08日", "我的日记" + (++start), "xxxxx");
            diarys.add(diary);
        }
        }

    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = 0;
                diarys.clear();
                geneDiarys(tag);
                // mAdapter.notifyDataSetChanged();
                dAdapter = new MyDiaryAdapter(MainActivity.this, R.layout.diary_item, diarys);
                mListView.setAdapter(dAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneDiarys(tag);
                dAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
