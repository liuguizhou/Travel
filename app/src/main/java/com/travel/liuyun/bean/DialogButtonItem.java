package com.travel.liuyun.bean;

import java.io.Serializable;

/**
 * Created by tan_ on 14-10-28.
 */
public class DialogButtonItem implements Serializable {
    private int rid; //文字
    private String title; //文字
    private int buttonBg;//背景
    private int itemColor;//文字颜色

    public DialogButtonItem() {
    }

    public DialogButtonItem(int rid, int itemColor) {
        this.rid = rid;
        this.itemColor = itemColor;
    }

    public DialogButtonItem(String title, int itemColor) {
        this.title = title;
        this.itemColor = itemColor;
    }

    public DialogButtonItem(int rid, int buttonBg, int itemColor) {
        this.rid = rid;
        this.buttonBg = buttonBg;
        this.itemColor = itemColor;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getButtonBg() {
        return buttonBg;
    }

    public void setButtonBg(int buttonBg) {
        this.buttonBg = buttonBg;
    }

    public int getItemColor() {
        return itemColor;
    }

    public void setItemColor(int itemColor) {
        this.itemColor = itemColor;
    }

    public boolean isShowBg() {
        return this.buttonBg != 0;
    }
}
