package com.appdeveloper.android.smartdialog.queue;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.appdeveloper.android.smartdialog.BSRestorableDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shanchengyu on 12/21/17.
 */

public abstract class BSBaseQueueDialog extends BSRestorableDialog {
    public static final String QUEUE_CATEGORY_KEY = "QUEUE_CATEGORY_KEY";
    private int mHostHashCode;
    static Map<String, BSDialogQueueCenter> mDialogCategoryToQueueCenterMap;

    public static void showDialog(BSBaseQueueDialogInfo dialogInfo) {
        if (dialogInfo == null) {
            return;
        }

        String queueCategory = dialogInfo.getQueueCategory();
        if (mDialogCategoryToQueueCenterMap == null) {
            mDialogCategoryToQueueCenterMap = new HashMap<>();
        }
        BSDialogQueueCenter queueCenter = mDialogCategoryToQueueCenterMap.get(queueCategory);
        if (queueCenter == null) {
            queueCenter = new BSDialogQueueCenter();
            mDialogCategoryToQueueCenterMap.put(queueCategory, queueCenter);
        }
        queueCenter.enqueueDialogInfo(dialogInfo);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isShowFromActivity()) {
            mHostHashCode = getActivity().hashCode();
        } else {
            mHostHashCode = getParentFragment().hashCode();
        }
    }

    @CallSuper
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        String queueCategory = (String) getArguments().get(QUEUE_CATEGORY_KEY);
        BSDialogQueueCenter queueCenter = mDialogCategoryToQueueCenterMap.get(queueCategory);
        queueCenter.markHostIsShowDialog(getHostHashCode(), false);
        queueCenter.showNextDialog(getHostHashCode());
    }

    private int getHostHashCode() {
        return mHostHashCode;
    }
}
