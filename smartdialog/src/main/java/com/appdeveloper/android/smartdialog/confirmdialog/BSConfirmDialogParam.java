package com.appdeveloper.android.smartdialog.confirmdialog;

import android.content.DialogInterface;

import com.appdeveloper.android.smartdialog.BSRestorableDialog;
import com.appdeveloper.android.smartdialog.queue.BSBaseQueueDialogParam;

/**
 * Created by shanchengyu on 12/19/17.
 */

public class BSConfirmDialogParam extends BSBaseQueueDialogParam {
    private CharSequence mTitle, mMessage, mPositiveButtonText, mNegativeButtonText, mNeutralButtonText;
    private DialogInterface.OnClickListener mOnClickListener;
    private BSStyleParam mStyleParams;

    public BSConfirmDialogParam(Object activityOrFragment,
                                CharSequence title,
                                CharSequence message,
                                CharSequence positiveButtonText,
                                CharSequence negativeButtonText,
                                CharSequence neutralButtonText,
                                BSStyleParam styleParams,
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
        mStyleParams = styleParams;
    }

    @Override
    public BSRestorableDialog buildDialog() {
        BSConfirmDialog dialog = BSConfirmDialog.newInstance(mTitle, mMessage, mPositiveButtonText, mNegativeButtonText, mNeutralButtonText, getQueueCategory(), mStyleParams);
        dialog.setCancelable(false);
        dialog.setTag(getTag()).setAutoRestore(isAutoRestore());
        dialog.setOnClickListener(mOnClickListener);

        return dialog;
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
