package com.appdeveloper.android.smartdialog.queue;

import java.lang.ref.WeakReference;

/**
 * Created by shanchengyu on 12/21/17.
 */

public abstract class BSBaseQueueDialogParam implements BSQueueDialogInterface {
    private WeakReference<?> mHostWeakRef;
    private boolean mAutoRestore;
    private String mTag;

    public BSBaseQueueDialogParam(Object activityOrFragment,
                                  boolean autoRestore,
                                  String tag) {
        mHostWeakRef = new WeakReference<>(activityOrFragment);
        mAutoRestore = autoRestore;
        mTag = tag;
    }

    public WeakReference<?> getHostWeakRef() {
        return mHostWeakRef;
    }

    public void setHostWeakRef(WeakReference<?> hostWeakRef) {
        mHostWeakRef = hostWeakRef;
    }

    public boolean isAutoRestore() {
        return mAutoRestore;
    }

    public void setAutoRestore(boolean autoRestore) {
        mAutoRestore = autoRestore;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }
}
