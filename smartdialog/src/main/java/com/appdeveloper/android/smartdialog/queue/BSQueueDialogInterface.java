package com.appdeveloper.android.smartdialog.queue;

import com.appdeveloper.android.smartdialog.BSSmartDialog;

/**
 * Created by shanchengyu on 12/21/17.
 */

interface BSQueueDialogInterface {
    String getQueueCategory();
    BSSmartDialog buildDialog();
}
