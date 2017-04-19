package com.travel.liuyun.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuguizhou on 2017/4/10.
 */

public class Man implements Parcelable {

    private int idCard;
    private String name;

    public Man(int idCard, String name) {
        this.idCard = idCard;
        this.name = name;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idCard);
        dest.writeString(this.name);
    }

    public Man() {
    }

    protected Man(Parcel in) {
        this.idCard = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Man> CREATOR = new Parcelable.Creator<Man>() {
        @Override
        public Man createFromParcel(Parcel source) {
            return new Man(source);
        }

        @Override
        public Man[] newArray(int size) {
            return new Man[size];
        }
    };
}
