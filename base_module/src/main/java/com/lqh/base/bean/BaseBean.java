package com.lqh.base.bean;

import java.io.Serializable;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public abstract class BaseBean implements Serializable{

    long tag;

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }

    public static boolean isCorrect(BaseBean data) {
        return data != null && data.isCorrect();
    }

    /**
     * 数据正确性校验
     * public导致JSON.toJSONString会添加correct字段
     * @return
     */
    protected abstract boolean isCorrect();

}
