package com.docwei.navbtndemo;

/**
 * Created by tobo on 17/8/5.
 * 默认数据源是文字和图标id
 */

public class NavigtionButtonItem {
    public int iconId;
    public String desc;
    public int activeColor;

    public NavigtionButtonItem(int iconId, String desc) {
        this.iconId = iconId;
        this.desc = desc;
    }

    public NavigtionButtonItem setActiveColor(int activeColor) {
        this.activeColor = activeColor;
        return this;
    }
}
