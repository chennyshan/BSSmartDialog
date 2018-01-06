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
    private SparseArray<Queue<BSBaseQueueDialogParam>> mHostHashCodeWithMessageDialogQueueInfoMap = new SparseArray<>();// K:activityOrFragment.hashCode(); V:dialog info queue
    private Set<Integer> mShowingDialogHostHashCodes = new ArraySet<>();// 如果activity/fragment上有dialog正在显示，则记录下该activity/fragment的hashcode

    public void enqueueDialogParam(BSBaseQueueDialogParam dialogParam) {
        if (dialogParam == null || dialogParam.getHostWeakRef().get() == null) {
            return;
        }

        Object activityOrFragment = dialogParam.getHostWeakRef().get();
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
            Queue<BSBaseQueueDialogParam> msgDialogParamQueue = mHostHashCodeWithMessageDialogQueueInfoMap.get(mapKey);
            if (msgDialogParamQueue == null) {
                msgDialogParamQueue = new LinkedList<>();
                mHostHashCodeWithMessageDialogQueueInfoMap.put(mapKey, msgDialogParamQueue);
            }
            msgDialogParamQueue.add(dialogParam);
            showNextDialog(mapKey);
        }
    }

    public void showNextDialog(int mapKey) {
        if (hostIsShowingDialog(mapKey)) {
            return;
        }
        Queue<BSBaseQueueDialogParam> msgDialogParamQueue = mHostHashCodeWithMessageDialogQueueInfoMap.get(mapKey);
        if (msgDialogParamQueue != null) {
            if (!msgDialogParamQueue.isEmpty()) {
                BSBaseQueueDialogParam dialogParam = msgDialogParamQueue.poll();
                while (dialogParam != null) {
                    Object context = dialogParam.getHostWeakRef().get();
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
                            dialogParam.buildDialog().show(isActivityContext ? ((FragmentActivity) activity).getSupportFragmentManager() : ((Fragment) context).getChildFragmentManager());
                        } else {
                            Assert.assertTrue("Only support FragmentActivity(AppCompatActivity), your activity:" + activity.getClass().getSimpleName(), false);
                        }

                        markHostIsShowDialog(mapKey, true);
                        break;
                    } else {
                        dialogParam = msgDialogParamQueue.poll();
                    }
                }

                if (msgDialogParamQueue.isEmpty()) {
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
