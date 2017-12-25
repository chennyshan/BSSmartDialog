package com.appdeveloper.android.smartdialog.confirmdialog;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;

/**
 * Created by shanchengyu on 12/24/17.
 */

public class BSStyleParams implements Parcelable {
    private int mTitleColor;
    private float mTitleSize;

    private int mMessageColor;
    private float mMessageSize;

    private int mPositiveButtonTextColor, mNegativeButtonTextColor, mNeutralButtonTextColor;

    public BSStyleParams() {}

    public int getTitleColor() {
        return mTitleColor;
    }

    public BSStyleParams setTitleColor(@ColorInt int titleColor) {
        mTitleColor = titleColor;
        return this;
    }

    public float getTitleSize() {
        return mTitleSize;
    }

    public BSStyleParams setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        return this;
    }

    public int getMessageColor() {
        return mMessageColor;
    }

    public BSStyleParams setMessageColor(@ColorInt int messageColor) {
        mMessageColor = messageColor;
        return this;
    }

    public float getMessageSize() {
        return mMessageSize;
    }

    public BSStyleParams setMessageSize(float messageSize) {
        mMessageSize = messageSize;
        return this;
    }

    public int getPositiveButtonTextColor() {
        return mPositiveButtonTextColor;
    }

    public BSStyleParams setPositiveButtonTextColor(@ColorInt int positiveButtonTextColor) {
        mPositiveButtonTextColor = positiveButtonTextColor;
        return this;
    }

    public int getNegativeButtonTextColor() {
        return mNegativeButtonTextColor;
    }

    public BSStyleParams setNegativeButtonTextColor(int negativeButtonTextColor) {
        mNegativeButtonTextColor = negativeButtonTextColor;
        return this;
    }

    public int getNeutralButtonTextColor() {
        return mNeutralButtonTextColor;
    }

    public BSStyleParams setNeutralButtonTextColor(@ColorInt int neutralButtonTextColor) {
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

    public static final Parcelable.Creator<BSStyleParams> CREATOR = new Creator<BSStyleParams>() {
        @Override
        public BSStyleParams[] newArray(int size)
        {
            return new BSStyleParams[size];
        }

        @Override
        public BSStyleParams createFromParcel(Parcel in)
        {
            return new BSStyleParams(in);
        }
    };

    public BSStyleParams(Parcel in) {
        mTitleColor = in.readInt();
        mTitleSize = in.readFloat();
        mMessageColor = in.readInt();
        mMessageSize = in.readFloat();
        mPositiveButtonTextColor = in.readInt();
        mNegativeButtonTextColor = in.readInt();
        mNeutralButtonTextColor = in.readInt();
    }
}
