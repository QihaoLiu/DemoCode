package com.lqh.demo.bean;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class Item {

    private String name;

    private boolean isGif;
    private int icon;

    public Item(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public Item(String name, boolean isGif, int icon) {
        this.name = name;
        this.isGif = isGif;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isGif() {
        return isGif;
    }

    public void setGif(boolean gif) {
        isGif = gif;
    }
}
