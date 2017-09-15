package com.docwei.navbtndemo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tobo on 17/8/5.
 * 这里提供两种类型:  文本框+图标(TextView+ImageView)         文本框+背景(TextView)
 */

public class NavigationChildView extends LinearLayout {

    private TextView mNavi_tv;
    private ImageView mNavi_iv;
    public NavigtionButtonItem mNBItem;
    public int mActiveColor;
    public int mUnActiveColor = getResources().getColor(R.color.unselected);
    private Drawable mOriginBitmapDrawable;
    public int index;
    public boolean isActived;
    private boolean mIsShowIcon;
    private TextView mNavi_onlytv;
    private GradientDrawable mOnlytv_shape;
    ;

    public NavigationChildView(Context context) {
        this(context, null);
    }

    public NavigationChildView(Context context, boolean isShowIcon) {
        this(context, null);
        mIsShowIcon = isShowIcon;
        init(context);
    }

    public NavigationChildView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationChildView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        if (mIsShowIcon) {
            View view = View.inflate(context, R.layout.layout_navi_child, this);
            mNavi_tv = (TextView) view.findViewById(R.id.child_navi_tv);
            mNavi_iv = (ImageView) view.findViewById(R.id.child_navi_iv);
        } else {
            //注意这里我写的是null上面写的是this,因为上一个根标签的merge标签
            mNavi_onlytv = (TextView) View.inflate(context, R.layout.layout_navi_onlytext, null);
            mNavi_onlytv.setGravity(Gravity.CENTER);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //如果要调整,请自行在代码里面修改吧,很简单, xml里面padding生效,margin不生效
            params.leftMargin = DensityUtil.dip2px(context, 10);
            params.rightMargin = DensityUtil.dip2px(context, 10);
            mNavi_onlytv.setLayoutParams(params);
            mNavi_onlytv.setTextColor(mUnActiveColor);
            //代码实现shape背景
            mOnlytv_shape = new GradientDrawable();
            mOnlytv_shape.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            mOnlytv_shape.setCornerRadius(5);
            mOnlytv_shape.setStroke(DensityUtil.dip2px(context, 1), mUnActiveColor);
            mNavi_onlytv.setBackgroundDrawable(mOnlytv_shape);
            addView(mNavi_onlytv);
        }
    }

    public void addItem(NavigtionButtonItem buttonItem) {
        mNBItem = buttonItem;
        if (mIsShowIcon) {
            mNavi_tv.setText(mNBItem.desc);
            mNavi_tv.setTextColor(getResources().getColor(R.color.unselected));
            mOriginBitmapDrawable = getResources().getDrawable(mNBItem.iconId);
            mActiveColor = getResources().getColor(mNBItem.activeColor);
        } else {
            mNavi_onlytv.setText(mNBItem.desc);
            mNavi_onlytv.setTextColor(getResources().getColor(R.color.unselected));
            mActiveColor = getResources().getColor(mNBItem.activeColor);
        }
    }

    public Drawable tintDrawable(Drawable drawable, ColorStateList color) {
        final Drawable tempDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(tempDrawable, color);
        return tempDrawable;
    }

    public void setActived(boolean isActive) {
        isActived = isActive;
        if (mIsShowIcon) {
            if (isActived) {
                mNavi_tv.setTextColor(mActiveColor);
                if (mOriginBitmapDrawable == null) {
                    mOriginBitmapDrawable = getResources().getDrawable(mNBItem.iconId);
                }
                mNavi_iv.setImageDrawable(tintDrawable(mOriginBitmapDrawable, ColorStateList.valueOf(mActiveColor)));
            } else {
                mNavi_tv.setTextColor(mUnActiveColor);

                if (mOriginBitmapDrawable == null) {
                    mOriginBitmapDrawable = getResources().getDrawable(mNBItem.iconId);
                }
                mNavi_iv.setImageDrawable(tintDrawable(mOriginBitmapDrawable, ColorStateList.valueOf(mUnActiveColor)));
            }
        } else {
            if (isActived) {
                mNavi_onlytv.setTextColor(mActiveColor);
                mOnlytv_shape.setStroke(DensityUtil.dip2px(getContext(), 1), mActiveColor);
            } else {
                mNavi_onlytv.setTextColor(mUnActiveColor);
                mOnlytv_shape.setStroke(DensityUtil.dip2px(getContext(), 1), mUnActiveColor);
            }
        }
    }

}
