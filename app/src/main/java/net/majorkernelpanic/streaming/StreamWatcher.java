package net.majorkernelpanic.streaming;

import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.util.Log;

import net.majorkernelpanic.streaming.audio.AudioQuality;
import net.majorkernelpanic.streaming.rtsp.RtspServer;
import net.majorkernelpanic.streaming.video.VideoQuality;

public class StreamWatcher implements Session.Callback {

    public static String TAG = "StreamManager";

    private static volatile StreamWatcher singleton;

    private StreamWatcher() {
    }

    public static StreamWatcher getInstance() {
        if (singleton == null) {
            synchronized (StreamWatcher.class) {
                if (singleton == null) {
                    singleton = new StreamWatcher();
                }
            }
        }
        return singleton;
    }

    public Context getContext() {
        return mContext;
    }

    private Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public void initStreaming(Context context) {
        mContext = context;
    }

    /**
     * 启动server
     */
    public void startStreamingServer() {
        if (mContext != null) {
            Log.d(TAG, "start rtsp server");
            mContext.startService(new Intent(mContext, RtspServer.class));
        } else {
            Log.d(TAG, "start rtsp server failed ! not init");
        }
    }

    /**
     * 停止server
     */
    public void stopStreamingServer() {
        if (mContext != null) {
            Log.d(TAG, "stop rtsp server");
            mContext.stopService(new Intent(mContext, RtspServer.class));
        } else {
            Log.d(TAG, "stop rtsp server failed ! not init");
        }
    }

    /**
     * 校验帧率和比特率
     *
     * @param frame
     * @param bitrate
     */
    private void checkFrameAndBitrate(int frame, int bitrate) {
        if (frame > 0) {
            setFrame(frame);
        }
        int r2Bitrate = width * height * 3;
        if (bitrate > 5000000) {
            setBitrate(5000000);
        }else if (bitrate > 0){
            setBitrate(bitrate);
        }else {
            setBitrate(Math.min(r2Bitrate, 5000000));
        }
    }

    /**
     * 获取Session接口
     *
     * @return
     */
    public Session getSession() {
        return mSession;
    }

    private Session mSession;

    private HandlerThread mHandlerThread;

    public HandlerThread getHandlerThread(){
        Log.d(TAG,mHandlerThread == null?"HandlerThread NULL":mHandlerThread.isInterrupted()?"HandlerThread Interrupted":"HandlerThread OK");
        if (mHandlerThread == null || mHandlerThread.isInterrupted()) {
            mHandlerThread = new HandlerThread("QihaoLiu.Session");
            mHandlerThread.start();
        }
        return mHandlerThread;
    }

    public void buildStreaming(net.majorkernelpanic.streaming.gl.SurfaceView surfaceView, int width, int height, int degree, int frame, int bitrate) {
        if (mContext == null) {
            Log.d(TAG, "build streaming with camera failed ! not init");
            return;
        }
        Log.d(TAG, "build streaming with camera <" + width + "," + height + "," + degree + "," + frame + "," + bitrate + ">");
        this.width = width;
        this.height = height;
        checkFrameAndBitrate(frame, bitrate);
        mSession = SessionBuilder.getInstance()
                .setPreviewOrientation(degree)
                .setSurfaceView(surfaceView)
                .setContext(mContext)
                .setAudioEncoder(SessionBuilder.AUDIO_AAC)
                .setAudioQuality(new AudioQuality(16000, 32000))
                .setVideoEncoder(SessionBuilder.VIDEO_H264)
                .setVideoQuality(new VideoQuality(width, height, getFrame(), getBitrate()))
                .setCallback(this)
                .build();
    }

    @Override
    public void onBitrateUpdate(long bitrate) {

    }

    @Override
    public void onSessionError(int reason, int streamType, Exception e) {
        Log.d(TAG, "session error " + reason);
        Log.d(TAG, "session error " + streamType);
        Log.d(TAG, "session error " + e.toString());
    }

    @Override
    public void onPreviewStarted() {
        Log.d(TAG, "session preview start");
    }

    @Override
    public void onSessionConfigured() {
        Log.d(TAG, "session configured");
    }

    @Override
    public void onSessionStarted() {
        Log.d(TAG, "session start");
    }

    @Override
    public void onSessionStopped() {
        Log.d(TAG, "session stop");
    }

    private int density;
    private int width;
    private int height;

    public int getDensity() {
        return density;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int bitrate = 5000000;

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    private int frame = 25;

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

}