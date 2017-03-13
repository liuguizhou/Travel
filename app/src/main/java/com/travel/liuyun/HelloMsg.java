package com.travel.liuyun;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuguizhou on 2016/9/19.
 */

public class HelloMsg implements Parcelable {
    private int id;
    private String msg;

    public HelloMsg(String msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.msg);
    }

    protected HelloMsg(Parcel in) {
        this.id = in.readInt();
        this.msg = in.readString();
    }

    public static final Creator<HelloMsg> CREATOR = new Creator<HelloMsg>() {
        @Override
        public HelloMsg createFromParcel(Parcel source) {
            return new HelloMsg(source);
        }

        @Override
        public HelloMsg[] newArray(int size) {
            return new HelloMsg[size];
        }
    };
}
