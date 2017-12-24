package com.appdeveloper.android.smartdialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by shanchengyu on 12/21/17.
 */

public class BSSmartDialogUtils {
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
