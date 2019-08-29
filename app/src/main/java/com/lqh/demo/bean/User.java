package com.lqh.demo.bean;

import com.lqh.base.bean.BaseBean;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class User extends BaseBean{

    private int id;

    private String name;

    public User() {

    }

    public User(int id) {
        this();
        this.id = id;
    }

    public User(int id, String name) {
        this(id);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected boolean isCorrect() {
        return getTag()>0;
    }
}
