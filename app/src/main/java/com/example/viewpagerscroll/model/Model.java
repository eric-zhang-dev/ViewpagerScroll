package com.example.viewpagerscroll.model;

/**
 * Created by zhangyue on 2016/5/20.
 */
public class Model {
    private String title;
    private int icon;

    public Model(int icon,String title) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
