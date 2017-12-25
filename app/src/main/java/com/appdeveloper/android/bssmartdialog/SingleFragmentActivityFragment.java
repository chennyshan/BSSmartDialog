package com.appdeveloper.android.bssmartdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appdeveloper.android.smartdialog.confirmdialog.BSConfirmDialogUtils;
import com.appdeveloper.android.smartdialog.BSSmartDialog;
import com.appdeveloper.android.smartdialog.BSViewHolder;
import com.appdeveloper.android.smartdialog.confirmdialog.BSStyleParams;

/**
 * A placeholder fragment containing a simple view.
 */
public class SingleFragmentActivityFragment extends Fragment implements BSSmartDialog.OnSmartDialogCreateListener, DialogInterface.OnClickListener {

    private static String TagMainActivityFragment = "MainActivityFragment";

    public void showCustomLayoutId(View view) {
        BSSmartDialog smartDialog = BSSmartDialog.newInstance()
                .setLayoutId(R.layout.share_layout)
                .setDimAmount(0.3f)
                .setShowBottom(true);
        smartDialog.setAutoRestore(true);
        smartDialog.setOnSmartDialogCreateListener(this);
        smartDialog.show(getChildFragmentManager());
    }

    public void showMaterialDesignAlert(View view) {
        BSStyleParams params = new BSStyleParams()
                .setTitleSize(20)
                .setTitleColor(Color.RED)
                .setMessageSize(16)
                .setMessageColor(Color.BLUE)
                .setPositiveButtonTextColor(Color.parseColor("#889122"));
        BSConfirmDialogUtils.showAlertDialog(this,
                "title1", "怎么理解呢？简单解释：在ScrollView往上滑动时，首先是View把滑动事件“夺走”，由View去执行滑动，直到滑动最小高度后，把这个滑动事件“还”回去，让ScrollView内部去上滑。看个gif感受一下（图中将高度设的比较大:200dp，并将最小高度设置为",
                "positive", "negative", "neutral", params,
                true, this, TagMainActivityFragment);
    }

    public void showQueueAlert(View view) {
        BSStyleParams params = new BSStyleParams()
                .setTitleSize(20)
                .setTitleColor(Color.RED)
                .setMessageSize(16)
                .setMessageColor(Color.BLUE)
                .setPositiveButtonTextColor(Color.parseColor("#889122"));
        BSConfirmDialogUtils.showAlertDialog(this,
                "title1", "怎么理解呢？简单解释：在ScrollView往上滑动时，首先是View把滑动事件“夺走”，由View去执行滑动，直到滑动最小高度后，把这个滑动事件“还”回去，让ScrollView内部去上滑。看个gif感受一下（图中将高度设的比较大:200dp，并将最小高度设置为",
                "positive", "negative", "neutral", params,
                false, null, "Tag_showQueueAlert");
        BSConfirmDialogUtils.showAlertDialog(this,
                "title2", "开发过程中",
                "positive", null, null, null,
                false, this, "Tag_showQueueAlert");
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getActivity(), "fragment alert onClick which:" + which, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSmartDialogCreate(BSViewHolder holder, BSSmartDialog dialog) {
        holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "分享成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
//        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        View rootView = inflater.inflate(R.layout.content_dialog_infragment, container, false);
        Button shareBtn = (Button) rootView.findViewById(R.id.id_fragment_custom_layout);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomLayoutId(v);
            }
        });

        Button alertBtn = (Button)rootView.findViewById(R.id.id_showMaterialDesignAlert);
        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMaterialDesignAlert(v);
            }
        });

        Button queueBtn = (Button)rootView.findViewById(R.id.id_queue_dialog_fragment);
        queueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQueueAlert(v);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.v(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onDestroyView();
    }
}
