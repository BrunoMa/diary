package com.example.administrator.anonymous_diary;

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

public class EditDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        Bundle bundle = this.getIntent().getExtras();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Date today = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日");
        String todaydate = dateFormat.format(today);
        TextView dateview = (TextView) findViewById(R.id.update_diary_tv_date);
        if(bundle.getString("date").equals(todaydate)){
            dateview.setText("今天，"+bundle.getString("date"));
        } else {
            dateview.setText(bundle.getString("date"));
        }
        EditText titletext = (EditText) findViewById(R.id.update_diary_et_title);
        titletext.setText(bundle.getString("title"));
        EditText contenttext = (EditText) findViewById(R.id.update_diary_et_content);
        contenttext.setText(bundle.getString("content"));
        TextView tvtitle = (TextView) findViewById((R.id.common_tv_title));
        tvtitle.setText("");

        ImageView back = (ImageView) findViewById(R.id.common_iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.startActivity(EditDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton add = (FloatingActionButton) findViewById(R.id.update_diary_fab_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditDiaryActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                MainActivity.startActivity(EditDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton back2 = (FloatingActionButton) findViewById(R.id.update_diary_fab_back);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditDiaryActivity.this, "取消修改", Toast.LENGTH_SHORT).show();
                MainActivity.startActivity(EditDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        final FloatingActionButton delete = (FloatingActionButton) findViewById(R.id.update_diary_fab_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditDiaryActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                MainActivity.startActivity(EditDiaryActivity.this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
