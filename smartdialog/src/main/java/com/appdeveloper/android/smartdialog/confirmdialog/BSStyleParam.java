package com.appdeveloper.android.smartdialog.confirmdialog;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;

/**
 * Created by shanchengyu on 12/24/17.
 */

public class BSStyleParam implements Parcelable {
    private int mTitleColor;
    private float mTitleSize;

    private int mMessageColor;
    private float mMessageSize;

    private int mPositiveButtonTextColor, mNegativeButtonTextColor, mNeutralButtonTextColor;

    public BSStyleParam() {}

    public int getTitleColor() {
        return mTitleColor;
    }

    public BSStyleParam setTitleColor(@ColorInt int titleColor) {
        mTitleColor = titleColor;
        return this;
    }

    public float getTitleSize() {
        return mTitleSize;
    }

    public BSStyleParam setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        return this;
    }

    public int getMessageColor() {
        return mMessageColor;
    }

    public BSStyleParam setMessageColor(@ColorInt int messageColor) {
        mMessageColor = messageColor;
        return this;
    }

    public float getMessageSize() {
        return mMessageSize;
    }

    public BSStyleParam setMessageSize(float messageSize) {
        mMessageSize = messageSize;
        return this;
    }

    public int getPositiveButtonTextColor() {
        return mPositiveButtonTextColor;
    }

    public BSStyleParam setPositiveButtonTextColor(@ColorInt int positiveButtonTextColor) {
        mPositiveButtonTextColor = positiveButtonTextColor;
        return this;
    }

    public int getNegativeButtonTextColor() {
        return mNegativeButtonTextColor;
    }

    public BSStyleParam setNegativeButtonTextColor(int negativeButtonTextColor) {
        mNegativeButtonTextColor = negativeButtonTextColor;
        return this;
    }

    public int getNeutralButtonTextColor() {
        return mNeutralButtonTextColor;
    }

    public BSStyleParam setNeutralButtonTextColor(@ColorInt int neutralButtonTextColor) {
        mNeutralButtonTextColor = neutralButtonTextColor;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTitleColor);
        dest.writeFloat(mTitleSize);
        dest.writeInt(mMessageColor);
        dest.writeFloat(mMessageSize);
        dest.writeInt(mPositiveButtonTextColor);
        dest.writeInt(mNegativeButtonTextColor);
        dest.writeInt(mNeutralButtonTextColor);
    }

    public static final Parcelable.Creator<BSStyleParam> CREATOR = new Creator<BSStyleParam>() {
        @Override
        public BSStyleParam[] newArray(int size)
        {
            return new BSStyleParam[size];
        }

        @Override
        public BSStyleParam createFromParcel(Parcel in)
        {
            return new BSStyleParam(in);
        }
    };

    public BSStyleParam(Parcel in) {
        mTitleColor = in.readInt();
        mTitleSize = in.readFloat();
        mMessageColor = in.readInt();
        mMessageSize = in.readFloat();
        mPositiveButtonTextColor = in.readInt();
        mNegativeButtonTextColor = in.readInt();
        mNeutralButtonTextColor = in.readInt();
    }
}
