package com.lqh.demo.bean;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class Item {

    private String name;

    private int icon;

    public Item(String name, int icon) {
        this.name = name;
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
}
