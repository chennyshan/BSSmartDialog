package com.appdeveloper.android.smartdialog.queue;

import com.appdeveloper.android.smartdialog.BSRestorableDialog;

/**
 * Created by shanchengyu on 12/21/17.
 */

interface BSQueueDialogInterface {
    String getQueueCategory();
    BSRestorableDialog buildDialog();
}
