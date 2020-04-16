package org.nuc.listenphone.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;

public class ListenService extends Service {
    public ListenService() {
    }

    private TelephonyManager tm;
    private MediaRecorder recorder;
    MyListener myListener;

    @Override
    public void onCreate() {
        super.onCreate();
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        myListener = new MyListener();
        // 开始监听
        tm.listen(myListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消监听
        tm.listen(myListener, PhoneStateListener.LISTEN_NONE);
        myListener = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    //空闲
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //接电话
                    // 录音机
                    recorder = new MediaRecorder();
                    // 录音元 麦克风
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 录音格式 默认
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                    // 录音文件
                    File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".3gp");
                    recorder.setOutputFile(file.getAbsolutePath());
                    // 设定音频编码
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    try {
                        recorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recorder.start();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //响铃
                    break;
            }
        }
    }
}
