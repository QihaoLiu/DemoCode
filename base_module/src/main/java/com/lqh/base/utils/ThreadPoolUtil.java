package com.lqh.base.utils;

import com.lqh.base.pool.SingleThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class ThreadPoolUtil {

    private static volatile SingleThreadPool singleThreadPool;

    public static SingleThreadPool getSingleThreadPool(){
        if (singleThreadPool == null){
            synchronized (ThreadPoolExecutor.class){
                if (singleThreadPool == null){
                    singleThreadPool = new SingleThreadPool();
                }
            }
        }
        return singleThreadPool;
    }

    public static void shutDown(){
        if (singleThreadPool != null) {
            singleThreadPool.shutDown();
        }
    }

}
