package com.appdeveloper.android.smartdialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import junit.framework.Assert;

public class BSSmartDialog extends BSRestorableDialog {
    private static final String Margin = "margin";
    private static final String Width = "width";
    private static final String Height = "height";
    private static final String Dim = "dim_amount";
    private static final String Bottom = "show_bottom";
    private static final String AnimStyle = "anim_style";

    private static final String LayoutId = "LayoutId";

    private OnSmartDialogCreateListener mOnSmartDialogCreateListener;
    @LayoutRes
    protected int layoutId;

    private int margin;//左右边距
    private int width;
    private int height;
    private float dimAmount = 0.5f;
    private boolean showBottom;//是否底部显示
    @StyleRes
    private int animStyle;

    public static BSSmartDialog newInstance() {
        return new BSSmartDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SmartDialog);

        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(Margin);
            width = savedInstanceState.getInt(Width);
            height = savedInstanceState.getInt(Height);
            dimAmount = savedInstanceState.getFloat(Dim);
            showBottom = savedInstanceState.getBoolean(Bottom);
            animStyle = savedInstanceState.getInt(AnimStyle);
            layoutId = savedInstanceState.getInt(LayoutId);
        }

        if (isShowFromActivity()) {
            if (isAutoRestore()) {
                if (mOnSmartDialogCreateListener != null && getActivity() != mOnSmartDialogCreateListener) {
                    Assert.assertTrue("If set 'OnSmartDialogCreateListener', your activity should be set as OnSmartDialogCreateListener:" + getActivity().getClass().getSimpleName(), false);
                }
                if (savedInstanceState != null && getActivity() instanceof OnSmartDialogCreateListener) {
                    // don't keep activity情况下，listener会丢失，这里重新连上
                    mOnSmartDialogCreateListener = (OnSmartDialogCreateListener) getActivity();
                }
            }
        } else {
            Fragment parentFragment = getParentFragment();
            if (isAutoRestore()) {
                if (mOnSmartDialogCreateListener != null && parentFragment != mOnSmartDialogCreateListener) {
                    Assert.assertTrue("If set 'OnSmartDialogCreateListener', your fragment should be set as OnSmartDialogCreateListener", false);
                }
                if (savedInstanceState != null && parentFragment instanceof OnSmartDialogCreateListener) {
                    mOnSmartDialogCreateListener = (OnSmartDialogCreateListener) parentFragment;
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId != 0) {
            View view = inflater.inflate(layoutId, container, false);
            if (mOnSmartDialogCreateListener != null) {
                mOnSmartDialogCreateListener.onSmartDialogCreate(BSViewHolder.create(view), this);
            }
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Margin, margin);
        outState.putInt(Width, width);
        outState.putInt(Height, height);
        outState.putFloat(Dim, dimAmount);
        outState.putBoolean(Bottom, showBottom);
        outState.putInt(AnimStyle, animStyle);
        outState.putInt(LayoutId, layoutId);
    }

    public BSSmartDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BSSmartDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BSSmartDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BSSmartDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BSSmartDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public BSSmartDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BSSmartDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public BSSmartDialog setOnSmartDialogCreateListener(OnSmartDialogCreateListener onViewCreateListener) {
        this.mOnSmartDialogCreateListener = onViewCreateListener;
        return this;
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
                if (animStyle == 0) {
                    animStyle = R.style.DefaultAnimation;
                }
            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = BSUtils.getScreenWidth(getActivity()) - 2 * BSUtils.dp2px(getActivity(), margin);
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.width = BSUtils.dp2px(getActivity(), width);
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = BSUtils.dp2px(getActivity(), height);
            }

            window.setWindowAnimations(animStyle);
            window.setAttributes(lp);
        }
    }

    public interface OnSmartDialogCreateListener {
        void onSmartDialogCreate(BSViewHolder holder, BSSmartDialog dialog);
    }
}
