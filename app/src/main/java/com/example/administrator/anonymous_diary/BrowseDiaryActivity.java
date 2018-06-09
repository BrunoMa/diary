package com.example.administrator.anonymous_diary;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diary.utils.GetDate;
import com.diary.utils.StatusBarCompat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;

public class BrowseDiaryActivity extends AppCompatActivity {

    private int collected = 0;
    private int interested = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_diary);
        Bundle bundle = this.getIntent().getExtras();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Date today = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日");
        String todaydate = dateFormat.format(today);
        TextView dateview = (TextView) findViewById(R.id.browse_diary_tv_date);
        if(bundle.getString("date").equals(todaydate)){
            dateview.setText("今天，"+bundle.getString("date"));
        } else {
            dateview.setText(bundle.getString("date"));
        }
        TextView titleview = (TextView) findViewById(R.id.browse_diary_et_title);
        titleview.setText(bundle.getString("title"));
        TextView contentview = (TextView) findViewById(R.id.browse_diary_et_content);
        contentview.setText(bundle.getString("content"));
        TextView tvtitle = (TextView) findViewById((R.id.common_tv_title));
        tvtitle.setText("");

        ImageView back = (ImageView) findViewById(R.id.common_iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.startActivity(BrowseDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton collect = (FloatingActionButton) findViewById(R.id.update_diary_fab_collect);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collected == 0) {
                    collect.setColorNormal(Color.parseColor("#FFF68F"));
                    collected = 1;
                    Toast.makeText(BrowseDiaryActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }else{
                    collect.setColorNormal(Color.parseColor("#FFFAF0"));
                    collected = 0;
                    Toast.makeText(BrowseDiaryActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final FloatingActionButton interest = (FloatingActionButton) findViewById(R.id.update_diary_fab_interest);
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interested == 0) {
                    interest.setColorNormal(Color.parseColor("#FFF68F"));
                    interested = 1;
                    Toast.makeText(BrowseDiaryActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                }else{
                    interest.setColorNormal(Color.parseColor("#FFFAF0"));
                    interested = 0;
                    Toast.makeText(BrowseDiaryActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
