package net.majorkernelpanic.streaming;

import android.os.HandlerThread;

public class StreamWatcher {

    public StreamWatcher() {
    }

    private static StreamWatcher mInstance;

    public static StreamWatcher getmInstance() {
        if (mInstance == null) {
            synchronized (StreamWatcher.class) {
                mInstance = new StreamWatcher();
            }
        }
        return mInstance;
    }

    private HandlerThread mHandlerThread;

    public HandlerThread getHandlerThread(){
        if (mHandlerThread == null || mHandlerThread.isInterrupted()) {
            mHandlerThread = new HandlerThread("QihaoLiu.Session");
            mHandlerThread.start();
        }
        return mHandlerThread;
    }

}
