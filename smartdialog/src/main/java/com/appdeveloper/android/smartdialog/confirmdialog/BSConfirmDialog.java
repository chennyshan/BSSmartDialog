package com.appdeveloper.android.smartdialog.confirmdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.appdeveloper.android.smartdialog.R;
import com.appdeveloper.android.smartdialog.queue.BSBaseQueueDialog;

import junit.framework.Assert;

/**
 * Created by shanchengyu on 11/2/17.
 */

public class BSConfirmDialog extends BSBaseQueueDialog implements DialogInterface.OnClickListener {
    private static final String ConfirmDialog_Title = "ConfirmDialog_Title";
    private static final String ConfirmDialog_Message = "ConfirmDialog_Message";
    private static final String ConfirmDialog_PositiveButtonText = "ConfirmDialog_PositiveButtonText";
    private static final String ConfirmDialog_NegativeButtonText = "ConfirmDialog_NegativeButtonText";
    private static final String ConfirmDialog_NeutralButtonText = "ConfirmDialog_NeutralButtonText";
    private static final String ConfirmDialog_StyleParams = "ConfirmDialog_StyleParams";

    DialogInterface.OnClickListener mOnClickListener;

    public static BSConfirmDialog newInstance(@Nullable CharSequence title, @Nullable CharSequence message, @Nullable CharSequence positiveButtonText, @Nullable CharSequence negativeButtonText, @Nullable CharSequence neutralButtonText, String queueCategoryName, BSStyleParams styleParams) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(ConfirmDialog_Title, title);
        bundle.putCharSequence(ConfirmDialog_Message, message);
        bundle.putCharSequence(ConfirmDialog_PositiveButtonText, positiveButtonText);
        bundle.putCharSequence(ConfirmDialog_NegativeButtonText, negativeButtonText);
        bundle.putCharSequence(ConfirmDialog_NeutralButtonText, neutralButtonText);
        bundle.putCharSequence(QUEUE_CATEGORY_KEY, queueCategoryName);
        bundle.putParcelable(ConfirmDialog_StyleParams, styleParams);
        BSConfirmDialog alertDialog = new BSConfirmDialog();
        alertDialog.setArguments(bundle);
        return alertDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isShowFromActivity()) {
            if (isAutoRestore()) {
                if (mOnClickListener != null && getActivity() != mOnClickListener) {
                    // 传了onClickListener, 却又不是attached activity or fragment
                    Assert.assertTrue("If set 'autoRestore', your activity should be set as OnClickListener:" + getActivity().getClass().getSimpleName(), false);
                }

                if (savedInstanceState != null && getActivity() instanceof DialogInterface.OnClickListener) {
                    // don't keep activity情况下，listener会丢失，这里重新连上
                    mOnClickListener = (DialogInterface.OnClickListener) getActivity();
                }
            }
        } else {
            Fragment parentFragment = getParentFragment();
            if (isAutoRestore()) {
                if (mOnClickListener != null && parentFragment != mOnClickListener) {
                    Assert.assertTrue("If set 'autoRestore', your fragment should be set as parent and the OnClickListener", false);
                }
                if (savedInstanceState != null && parentFragment instanceof DialogInterface.OnClickListener) {
                    mOnClickListener = (DialogInterface.OnClickListener) parentFragment;
                }
            }
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialogTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getArguments().getCharSequence(ConfirmDialog_Title))
                .setMessage(getArguments().getCharSequence(ConfirmDialog_Message));
        CharSequence positiveText = getArguments().getCharSequence(ConfirmDialog_PositiveButtonText);
        if (!TextUtils.isEmpty(positiveText)) {
            builder.setPositiveButton(positiveText, this);
        }
        CharSequence negativeText = getArguments().getCharSequence(ConfirmDialog_NegativeButtonText);
        if (!TextUtils.isEmpty(negativeText)) {
            builder.setNegativeButton(negativeText, this);
        }
        CharSequence neutralText = getArguments().getCharSequence(ConfirmDialog_NeutralButtonText);
        if (!TextUtils.isEmpty(neutralText)) {
            builder.setNeutralButton(neutralText, this);
        }

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BSStyleParams styleParams = BSConfirmDialog.this.getArguments().getParcelable(ConfirmDialog_StyleParams);
                if (styleParams != null) {
                    // reference: http://blog.csdn.net/y12345654321/article/details/72673270
                    Window window = ((AlertDialog) dialog).getWindow();
                    TextView titleView = window.findViewById(R.id.alertTitle);
                    if (styleParams.getTitleSize() > 0) {
                        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, styleParams.getTitleSize());
                    }
                    if (styleParams.getTitleColor() != 0) {
                        titleView.setTextColor(styleParams.getTitleColor());
                    }

                    TextView messageView = window.findViewById(android.R.id.message);
                    if (styleParams.getMessageSize() > 0) {
                        messageView.setTextSize(TypedValue.COMPLEX_UNIT_SP, styleParams.getMessageSize());
                    }
                    if (styleParams.getMessageColor() != 0) {
                        messageView.setTextColor(styleParams.getMessageColor());
                    }
                    if (styleParams.getPositiveButtonTextColor() != 0) {
                        Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                        if (positiveButton != null) {
                            positiveButton.setTextColor(styleParams.getPositiveButtonTextColor());
                        }
                    }
                    if (styleParams.getNegativeButtonTextColor() != 0) {
                        Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                        if (negativeButton != null) {
                            negativeButton.setTextColor(styleParams.getNegativeButtonTextColor());
                        }
                    }
                    if (styleParams.getNeutralButtonTextColor() != 0) {
                        Button neutralButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEUTRAL);
                        if (neutralButton != null) {
                            neutralButton.setTextColor(styleParams.getNeutralButtonTextColor());
                        }
                    }
                }
            }
        });

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        this.dismiss();
        if (mOnClickListener != null) {
            mOnClickListener.onClick(dialog, which);
        }
    }

    public BSConfirmDialog setOnClickListener(DialogInterface.OnClickListener listener) {
        this.mOnClickListener = listener;
        return this;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mOnClickListener = null;//avoid memory leak if mOnClickListener is host(Activity or Fragment)
    }
}
