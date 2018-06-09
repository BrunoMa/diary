package com.diary.event;

/**
 * Created by Administrator on 2018/6/8.
 */

public class StartBrowseDiaryEvent {
    private int position;

    public StartBrowseDiaryEvent(int position) {
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
