package com.lqh.base.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class SingleThreadPool {

    private volatile ExecutorService mExecutor;


    public SingleThreadPool() {

    }

    private void initThreadPoolExecutor(){
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
            synchronized (SingleThreadPool.class){
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
                    ThreadFactory threadFactory = new ThreadFactoryBuilder()
                            .setNameFormat("Base_Single_Thread_%d")
                            .build();
                    mExecutor = new ThreadPoolExecutor(1,1,0L,
                            TimeUnit.MILLISECONDS,
                            new LinkedBlockingDeque<Runnable>(1024),
                            threadFactory,
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
    }

    public void execute(Runnable task){
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    public void shutDown() {
        if (mExecutor != null) {
            mExecutor.shutdown();
        }
    }
}
