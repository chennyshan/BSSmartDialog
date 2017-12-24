package com.appdeveloper.android.smartdialog.queue;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArraySet;
import android.util.SparseArray;

import junit.framework.Assert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by shanchengyu on 11/2/17.
 */

public class BSDialogQueueCenter {
    private SparseArray<Queue<BSBaseQueueDialogInfo>> mHostHashCodeWithMessageDialogQueueInfoMap = new SparseArray<>();// K:activityOrFragment.hashCode(); V:dialog info queue
    private Set<Integer> mShowingDialogHostHashCodes = new ArraySet<>();// 如果activity/fragment上有dialog正在显示，则记录下该activity/fragment的hashcode

    public void enqueueDialogInfo(BSBaseQueueDialogInfo dialogInfo) {
        if (dialogInfo == null || dialogInfo.getHostWeakRef().get() == null) {
            return;
        }

        Object activityOrFragment = dialogInfo.getHostWeakRef().get();
        boolean isValidHost = false;
        if (activityOrFragment instanceof Activity) {
            if (!((Activity)activityOrFragment).isFinishing() && !((Activity)activityOrFragment).isDestroyed()) {
                isValidHost = true;
            }
        } else if (activityOrFragment instanceof Fragment) {
            Activity hostActivity = ((Fragment)activityOrFragment).getActivity();
            if (hostActivity != null && !hostActivity.isFinishing() && !hostActivity.isDestroyed()) {
                isValidHost = true;
            }
        }

        if (isValidHost) {
            int mapKey = activityOrFragment.hashCode();
            Queue<BSBaseQueueDialogInfo> msgDialogInfoQueue = mHostHashCodeWithMessageDialogQueueInfoMap.get(mapKey);
            if (msgDialogInfoQueue == null) {
                msgDialogInfoQueue = new LinkedList<>();
                mHostHashCodeWithMessageDialogQueueInfoMap.put(mapKey, msgDialogInfoQueue);
            }
            msgDialogInfoQueue.add(dialogInfo);
            showNextDialog(mapKey);
        }
    }

    public void showNextDialog(int mapKey) {
        if (hostIsShowingDialog(mapKey)) {
            return;
        }
        Queue<BSBaseQueueDialogInfo> msgDialogInfoQueue = mHostHashCodeWithMessageDialogQueueInfoMap.get(mapKey);
        if (msgDialogInfoQueue != null) {
            if (!msgDialogInfoQueue.isEmpty()) {
                BSBaseQueueDialogInfo dialogInfo = msgDialogInfoQueue.poll();
                while (dialogInfo != null) {
                    Object context = dialogInfo.getHostWeakRef().get();
                    Activity activity = null;
                    boolean isActivityContext = false;
                    if (context != null) {
                        if (context instanceof Activity) {
                            activity = (Activity) context;
                            isActivityContext = true;
                        } else if (context instanceof Fragment) {
                            activity = ((Fragment)context).getActivity();
                        }
                    }
                    if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
//                        confirmDialog.setMargin(60)
                        if (activity instanceof FragmentActivity) {
                            dialogInfo.buildDialog().show(isActivityContext ? ((FragmentActivity) activity).getSupportFragmentManager() : ((Fragment) context).getChildFragmentManager());
                        } else {
                            Assert.assertTrue("Only support FragmentActivity(AppCompatActivity), your activity:" + activity.getClass().getSimpleName(), false);
                        }

                        markHostIsShowDialog(mapKey, true);
                        break;
                    } else {
                        dialogInfo = msgDialogInfoQueue.poll();
                    }
                }

                if (msgDialogInfoQueue.isEmpty()) {
                    mHostHashCodeWithMessageDialogQueueInfoMap.remove(mapKey);
                }
            } else {
                mHostHashCodeWithMessageDialogQueueInfoMap.remove(mapKey);
            }
        }
    }

    public void markHostIsShowDialog(int hostHashCode, boolean isShowing) {
        if (isShowing) {
            mShowingDialogHostHashCodes.add(hostHashCode);
        } else {
            mShowingDialogHostHashCodes.remove(hostHashCode);
        }
    }

    private boolean hostIsShowingDialog(int hostHashCode) {
        return mShowingDialogHostHashCodes.contains(hostHashCode);
    }
}
