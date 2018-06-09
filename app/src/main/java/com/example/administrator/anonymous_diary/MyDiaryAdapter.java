package com.example.administrator.anonymous_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diary.bean.DiaryBean;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/7.
 */

public class MyDiaryAdapter extends ArrayAdapter<DiaryBean> {
    private int resourceId;
    private int mEditPosition = -1;

    public MyDiaryAdapter(Context context, int textViewResourceId,
                        List<DiaryBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Date today = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日");
        String todaydate = dateFormat.format(today);
        DiaryBean diary = getItem(position); // 获取当前项的DiaryBean实例
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) view.findViewById (R.id.main_tv_date);
            viewHolder.title = (TextView) view.findViewById (R.id.main_tv_title);
            viewHolder.content = (TextView) view.findViewById(R.id.main_tv_content);
            viewHolder.mIvEdit = (ImageView) view.findViewById(R.id.main_iv_edit);
            viewHolder.mLl = (LinearLayout) view.findViewById(R.id.item_ll);
            viewHolder.circle = (ImageView) view.findViewById(R.id.main_iv_circle);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        if(diary.getDate().equals(todaydate)){
            viewHolder.circle.setImageResource(R.drawable.circle_orange);
        }
        viewHolder.date.setText(diary.getDate());
        viewHolder.title.setText(diary.getTitle());
        viewHolder.content.setText("         "+diary.getContent()+"\n\n\n\n\n\n\n");
        viewHolder.mIvEdit.setVisibility(View.INVISIBLE);
        return view;
    }

    class ViewHolder {

        TextView date;

        TextView title;

        TextView content;

        ImageView mIvEdit;

        LinearLayout mLl;

        ImageView circle;

    }
}
