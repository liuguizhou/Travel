package com.travel.liuyun.bean;

import java.io.Serializable;

/**
 * Created by Young Pioneers on 16/6/30.
 */
public class ItemBean implements Serializable {

    private String title;

    private boolean isChecked;

    public ItemBean(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
