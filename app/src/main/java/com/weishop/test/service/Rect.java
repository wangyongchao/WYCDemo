package com.weishop.test.service;

import android.os.Parcel;
import android.os.Parcelable;

public class Rect implements Parcelable {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public static final Parcelable.Creator<Rect> CREATOR = new Parcelable.Creator<Rect>() {
        @Override
        public Rect createFromParcel(Parcel source) {
            return new Rect(source);
        }

        @Override
        public Rect[] newArray(int size) {
            return new Rect[size];
        }
    };

    public Rect() {
    }

    private Rect(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(right);
        dest.writeInt(bottom);

    }

    @Override
    public String toString() {
        return super.toString() + "," + left + ":" + right + ":" + top + ":" + bottom;
    }
}
