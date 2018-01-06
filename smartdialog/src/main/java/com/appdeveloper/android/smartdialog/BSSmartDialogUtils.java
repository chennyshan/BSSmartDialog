package com.appdeveloper.android.smartdialog;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.appdeveloper.android.smartdialog.confirmdialog.BSConfirmDialogParam;
import com.appdeveloper.android.smartdialog.confirmdialog.BSStyleParam;
import com.appdeveloper.android.smartdialog.queue.BSBaseQueueDialog;

/**
 * Created by shanchengyu on 12/21/17.
 */

public class BSSmartDialogUtils {
    public static void showAlertDialog(Object activityOrFragment,
                                       CharSequence title,
                                       CharSequence message,
                                       CharSequence positiveButtonText) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, null, null, null, false, null, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                       CharSequence title,
                                       CharSequence message,
                                       CharSequence positiveButtonText,
                                       DialogInterface.OnClickListener onClickListener) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, null, null, null, false, onClickListener, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                       CharSequence title,
                                       CharSequence message,
                                       CharSequence positiveButtonText,
                                       CharSequence negativeButtonText,
                                       DialogInterface.OnClickListener onClickListener) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, negativeButtonText, null, null, false, onClickListener, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                       CharSequence title,
                                       CharSequence message,
                                       CharSequence positiveButtonText,
                                       CharSequence negativeButtonText,
                                       CharSequence neutralButtonText,
                                       BSStyleParam styleParam,
                                       boolean autoRestore,
                                       DialogInterface.OnClickListener onClickListener,
                                       String tag) {
        BSConfirmDialogParam dialogParam = new BSConfirmDialogParam(activityOrFragment, title, message, positiveButtonText, negativeButtonText, neutralButtonText, styleParam, autoRestore, onClickListener, tag);
        BSBaseQueueDialog.showDialog(dialogParam);
    }

    public static void dismiss(FragmentActivity hostActivity, String tag) {
        Fragment fragment = hostActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null && fragment instanceof DialogFragment) {
            DialogFragment df = (DialogFragment) fragment;
            df.dismiss();
        }
    }

    public static void dismiss(Fragment hostFragment, String tag) {
        Fragment fragment = hostFragment.getChildFragmentManager().findFragmentByTag(tag);
        if (fragment != null && fragment instanceof DialogFragment) {
            DialogFragment df = (DialogFragment) fragment;
            df.dismiss();
        }
    }
}
