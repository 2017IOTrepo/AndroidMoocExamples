package com.example.myalarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class ClockActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.clock);
        Log.d("zhazha", "music start");
        mediaPlayer = MediaPlayer.create(this,R.raw.bgm);
        mediaPlayer.start();
        new AlertDialog.Builder(this)
                .setMessage("时间到了！")
                .setTitle("闹钟")
                .setPositiveButton("关闭闹钟", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        ClockActivity.this.finish();
                    }
                }).show();

    }
}
