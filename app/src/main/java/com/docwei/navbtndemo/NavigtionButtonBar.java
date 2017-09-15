package com.docwei.navbtndemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by tobo on 17/8/5.
 * 这里做了按钮的互斥以及按钮点击回调
 */

public class NavigtionButtonBar extends LinearLayout{

    private boolean mIsShowIcon;

    public NavigtionButtonBar(Context context) {
        this(context,null);
    }

    public NavigtionButtonBar(Context context, AttributeSet attrs) {
       this(context,attrs,0);

    }

    public NavigtionButtonBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs!=null){
            TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.NavigtionButtonBar);
            //默认显示图标
            mIsShowIcon = array.getBoolean(R.styleable.NavigtionButtonBar_isshowicon,true);
            array.recycle();
        }
        setOrientation(HORIZONTAL);

    }
    public int index;
    public synchronized NavigtionButtonBar addItem(NavigtionButtonItem buttonItem){
        NavigationChildView childview = new NavigationChildView(getContext(),mIsShowIcon);
        childview .addItem(buttonItem);
        LayoutParams params=new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight=1;
        childview.setLayoutParams(params);
        childview.index=index;
        childview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationChildView childview= (NavigationChildView) v;
                childview.setActived(true);
                setOtherTab(childview.index);
                if(mTabSelectLinster!=null){
                    mTabSelectLinster.tabSelected(childview.index);
                }
            }
        });
        addView(childview);
        index++;
        return this;
    }

    private void setOtherTab(int index) {
        for(int i=0;i<getChildCount();i++){
            if(i!=index){
                NavigationChildView otherChild= (NavigationChildView) getChildAt(i);
                otherChild.setActived(false);
            }
        }
    }



    public interface TabSelectLinster{
        void tabSelected(int position);
    }
    public TabSelectLinster mTabSelectLinster;
    public void setTabSelectLinster(TabSelectLinster tabSelectLinster){
        mTabSelectLinster=tabSelectLinster;
    }
    public void setTab(int index){
        if(index<0){
            //设置所有的孩子都是未选中的
            for(int i=0;i<getChildCount();i++){
                NavigationChildView childView= (NavigationChildView) getChildAt(i);
                childView.setActived(false);
            }
            return;
        }
        for(int i=0;i<getChildCount();i++){
            NavigationChildView childView= (NavigationChildView) getChildAt(i);
            if(i==index){
                childView.setActived(true);
            }else{
                childView.setActived(false);
            }
        }
    }
}
