package com.appdeveloper.android.smartdialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;

import junit.framework.Assert;

/**
 * Created by shanchengyu on 12/25/17.
 */

public abstract class BSRestorableDialog extends AppCompatDialogFragment {
    private static final String AutoRestore = "AutoRestore";

    private boolean mAutoRestore;
    private String mTag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SmartDialog);

        if (savedInstanceState != null) {
            mAutoRestore = savedInstanceState.getBoolean(AutoRestore, false);

            if (!mAutoRestore) {
                this.dismiss();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AutoRestore, mAutoRestore);
    }

    public BSRestorableDialog setAutoRestore(boolean autoRestore) {
        this.mAutoRestore = autoRestore;
        return this;
    }

    public boolean isAutoRestore() {
        return mAutoRestore;
    }

    public boolean isShowFromActivity() {
        return getParentFragment() == null;
    }

    public BSRestorableDialog setTag(String tag) {
        this.mTag = tag;
        return this;
    }

    public BSRestorableDialog show(@NonNull FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, mTag != null ?  mTag : String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Assert.assertTrue("Pls call 'public BSRestorableDialog show'", false);
        super.show(manager, tag);
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        Assert.assertTrue("Pls call 'public BSRestorableDialog show'", false);
        return super.show(transaction, tag);
    }
}
