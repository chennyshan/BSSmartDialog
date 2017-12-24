package com.appdeveloper.android.smartdialog.alertdialog;

import android.content.DialogInterface;

import com.appdeveloper.android.smartdialog.BSSmartDialog;
import com.appdeveloper.android.smartdialog.queue.BSBaseQueueDialogInfo;

/**
 * Created by shanchengyu on 12/19/17.
 */

public class BSAlertDialogInfo extends BSBaseQueueDialogInfo {
    private CharSequence mTitle, mMessage, mPositiveButtonText, mNegativeButtonText, mNeutralButtonText;
    private DialogInterface.OnClickListener mOnClickListener;

    BSAlertDialogInfo(Object activityOrFragment,
                      CharSequence title,
                      CharSequence message,
                      CharSequence positiveButtonText,
                      CharSequence negativeButtonText,
                      CharSequence neutralButtonText,
                      boolean autoRestore,
                      DialogInterface.OnClickListener onClickListener,
                      String tag) {
        super(activityOrFragment, autoRestore, tag);
        mTitle = title;
        mMessage = message;
        mPositiveButtonText = positiveButtonText;
        mNegativeButtonText = negativeButtonText;
        mNeutralButtonText = neutralButtonText;
        mOnClickListener = onClickListener;
    }

    @Override
    public BSSmartDialog buildDialog() {
        BSAlertDialog alertDialog = BSAlertDialog.newInstance(mTitle, mMessage, mPositiveButtonText, mNegativeButtonText, mNeutralButtonText, getQueueCategory());
        alertDialog.setCancelable(false);
        alertDialog.setTag(getTag()).setAutoRestore(isAutoRestore());
        alertDialog.setOnClickListener(mOnClickListener);

        return alertDialog;
    }

    @Override
    public String getQueueCategory() {
        return getClass().getSimpleName();
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    public CharSequence getMessage() {
        return mMessage;
    }

    public void setMessage(CharSequence message) {
        mMessage = message;
    }

    public CharSequence getPositiveButtonText() {
        return mPositiveButtonText;
    }

    public void setPositiveButtonText(CharSequence positiveButtonText) {
        mPositiveButtonText = positiveButtonText;
    }

    public CharSequence getNegativeButtonText() {
        return mNegativeButtonText;
    }

    public void setNegativeButtonText(CharSequence negativeButtonText) {
        mNegativeButtonText = negativeButtonText;
    }

    public DialogInterface.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
