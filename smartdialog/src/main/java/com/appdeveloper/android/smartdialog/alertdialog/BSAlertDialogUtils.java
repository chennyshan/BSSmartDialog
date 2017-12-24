package com.appdeveloper.android.smartdialog.alertdialog;

import android.content.DialogInterface;

import com.appdeveloper.android.smartdialog.BSSmartDialogUtils;
import com.appdeveloper.android.smartdialog.queue.BSBaseQueueDialog;

/**
 * Created by shanchengyu on 12/21/17.
 */

public class BSAlertDialogUtils extends BSSmartDialogUtils {
    public static void showAlertDialog(Object activityOrFragment,
                                         CharSequence title,
                                         CharSequence message,
                                         CharSequence positiveButtonText) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, null, null, false, null, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                         CharSequence title,
                                         CharSequence message,
                                         CharSequence positiveButtonText,
                                         DialogInterface.OnClickListener onClickListener) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, null, null, false, onClickListener, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                         CharSequence title,
                                         CharSequence message,
                                         CharSequence positiveButtonText,
                                         CharSequence negativeButtonText,
                                         DialogInterface.OnClickListener onClickListener) {
        showAlertDialog(activityOrFragment, title, message, positiveButtonText, negativeButtonText, null, false, onClickListener, null);
    }

    public static void showAlertDialog(Object activityOrFragment,
                                       CharSequence title,
                                       CharSequence message,
                                       CharSequence positiveButtonText,
                                       CharSequence negativeButtonText,
                                       CharSequence neutralButtonText,
                                       boolean autoRestore,
                                       DialogInterface.OnClickListener onClickListener,
                                       String tag) {
        BSAlertDialogInfo dialogInfo = new BSAlertDialogInfo(activityOrFragment, title, message, positiveButtonText, negativeButtonText, neutralButtonText, autoRestore, onClickListener, tag);
        BSBaseQueueDialog.showDialog(dialogInfo);
    }
}
