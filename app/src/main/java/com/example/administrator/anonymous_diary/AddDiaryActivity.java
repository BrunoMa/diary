package com.example.administrator.anonymous_diary;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cc.trity.floatingactionbutton.FloatingActionButton;

public class AddDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Date today = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日");
        String todaydate = dateFormat.format(today);
        TextView dateview = (TextView) findViewById(R.id.update_diary_tv_date);
        dateview.setText("今天，"+todaydate);
        EditText titletext = (EditText) findViewById(R.id.update_diary_et_title);
        titletext.setText("");
        EditText contenttext = (EditText) findViewById(R.id.update_diary_et_content);
        contenttext.setText("");
        TextView tvtitle = (TextView) findViewById((R.id.common_tv_title));
        tvtitle.setText("");

        ImageView back = (ImageView) findViewById(R.id.common_iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.startActivity(AddDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton add = (FloatingActionButton) findViewById(R.id.update_diary_fab_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddDiaryActivity.this, "发布", Toast.LENGTH_SHORT).show();
                MainActivity.startActivity(AddDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton back2 = (FloatingActionButton) findViewById(R.id.update_diary_fab_back);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddDiaryActivity.this, "取消", Toast.LENGTH_SHORT).show();
                MainActivity.startActivity(AddDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
