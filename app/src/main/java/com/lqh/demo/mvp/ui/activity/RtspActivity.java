package com.lqh.demo.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.lqh.base.activity.BaseActivity;
import com.lqh.demo.R;

import net.majorkernelpanic.streaming.Session;
import net.majorkernelpanic.streaming.SessionBuilder;
import net.majorkernelpanic.streaming.audio.AudioQuality;
import net.majorkernelpanic.streaming.gl.SurfaceView;
import net.majorkernelpanic.streaming.rtsp.RtspServer;
import net.majorkernelpanic.streaming.video.VideoQuality;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class RtspActivity extends BaseActivity implements SurfaceHolder.Callback , Session.Callback{

    public static final String TAG = "LeoLiu";

    private TextView mTips;
    private SurfaceView mSurfaceView;
    private Session mSession;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rtsp;
    }

    @Override
    public void setupActivityComponent() {

    }

    @Override
    public void initView() {
        super.initView();
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);
        mTips = (TextView) findView(R.id.tips);
    }

    @Override
    public void initData() {
        super.initData();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(RtspServer.KEY_PORT, String.valueOf(1234));
        editor.commit();
        String address = NetworkUtils.getIPAddress(true) + ":1234";
        Log.d(TAG,"rtsp init data address "+ address);
        mTips.setText(address);
        mSession = SessionBuilder.getInstance()
                .setSurfaceView(mSurfaceView)
                .setPreviewOrientation(0)
                .setContext(getApplicationContext())
                .setAudioEncoder(SessionBuilder.AUDIO_AAC)
                .setAudioQuality(new AudioQuality(16000, 32000))
                .setVideoEncoder(SessionBuilder.VIDEO_H264)
                .setVideoQuality(new VideoQuality(320, 240, 20, 500000))
                .setCallback(this)
                .build();

        this.startService(new Intent(this, RtspServer.class));

        mSurfaceView.getHolder().addCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stopService(new Intent(this, RtspServer.class));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG,"surface created session start preview");
        mSession.startPreview();
        int previewWidth = mSession.getVideoTrack().getVideoQuality().resX;
        int previewHeight = mSession.getVideoTrack().getVideoQuality().resY;
        Log.d(TAG,"surface created preview x "+previewWidth);
        Log.d(TAG,"surface created preview y "+previewHeight);
        RelativeLayout.LayoutParams textureLp = (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();
        int height = mSurfaceView.getHeight();
        double width = previewWidth * 1.0 * height / previewHeight;
        textureLp.width = (int) width;
        mSurfaceView.setLayoutParams(textureLp);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG,"surface destroyed session stop");
        mSession.stop();
    }

    @Override
    public void onBitrateUpdate(long bitrate) {

    }

    @Override
    public void onSessionError(int reason, int streamType, Exception e) {
        Log.d(TAG,"session error "+reason);
        Log.d(TAG,"session error "+streamType);
        Log.d(TAG,"session error "+e.toString());
    }

    @Override
    public void onPreviewStarted() {

    }

    @Override
    public void onSessionConfigured() {
        Log.d(TAG,"session configured");
        mSession.start();
    }

    @Override
    public void onSessionStarted() {

    }

    @Override
    public void onSessionStopped() {

    }

}
