package com.appdeveloper.android.bssmartdialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.appdeveloper.android.smartdialog.BSSmartDialogUtils;
import com.appdeveloper.android.smartdialog.BSSmartDialog;
import com.appdeveloper.android.smartdialog.BSViewHolder;
import com.appdeveloper.android.smartdialog.confirmdialog.BSStyleParam;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, BSSmartDialog.OnSmartDialogCreateListener {
    public static final String Tag_MaterialDesignAlert = "MaterialDesignAlert";
    private static final String Tag_ShareDialog = "Tag_ShareDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showCustomLayoutId(View view) {
        BSSmartDialog smartDialog = BSSmartDialog.newInstance()
                .setLayoutId(R.layout.share_layout)
                .setDimAmount(0.3f)
                .setShowBottom(true)
                .setOnSmartDialogCreateListener(this);
        smartDialog.setAutoRestore(true).setTag(Tag_ShareDialog);
        smartDialog.show(getSupportFragmentManager());
    }

    public void showMaterialDesignAlert(View view) {
        BSStyleParam params = new BSStyleParam()
                .setTitleSize(20)
                .setTitleColor(Color.RED)
                .setMessageSize(16)
                .setMessageColor(Color.BLUE)
                .setPositiveButtonTextColor(Color.parseColor("#889122"));
        BSSmartDialogUtils.showAlertDialog(this,
                "title1", "Alert,autoRestore=true, onClickListener=this",
                "positive", "negative", "neutral", params,
                true, this, Tag_MaterialDesignAlert);
    }

    public void showQueueDialog(View view) {
        BSSmartDialogUtils.showAlertDialog(this,
                "title1", "怎么理解呢？简单解释：在ScrollView往上滑动时，首先是View把滑动事件“夺走”，由View去执行滑动，直到滑动最小高度后，把这个滑动事件“还”回去，让ScrollView内部去上滑。看个gif感受一下（图中将高度设的比较大:200dp，并将最小高度设置为",
                "positive", "negative", "neutral", null,
                false, null, Tag_MaterialDesignAlert);
        BSSmartDialogUtils.showAlertDialog(this,
                "title2", "开发过程中",
                "positive", null, null, null,
                false, this, Tag_MaterialDesignAlert);
    }

    public void pushFragmentActivity(View view) {
        SingleFragmentActivity.startActivity(MainActivity.this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(MainActivity.this, "onClick which:" + which, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSmartDialogCreate(BSViewHolder holder, BSSmartDialog dialog) {
        holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                BSSmartDialogUtils.dismiss(MainActivity.this, Tag_ShareDialog);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
