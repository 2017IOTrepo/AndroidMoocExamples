package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btnStart, btnStop;
    private TextView tvInfo;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent intent;
    private int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(intent);

                Calendar currentTime = Calendar.getInstance();
                new TimePickerDialog(MainActivity.this, 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        long sysTime = System.currentTimeMillis();
                        c.set(Calendar.HOUR, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.set(Calendar.SECOND, 0);
                        c.set(Calendar.MILLISECOND, 0);
                        long selTime = c.getTimeInMillis();
                        Log.d("zhazha", String.valueOf(selTime));
                        Log.d("zhazha", String.valueOf(sysTime));
                        if (sysTime > selTime) {
                            c.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        tvInfo.setText("设定的定时闹钟时间：" + hourOfDay + ":" + minute);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                            Log.d("zhazha", String.valueOf(alarmManager.getNextAlarmClock().getTriggerTime()));

//                            alarmManager.set(AlarmManager.RTC, SystemClock.elapsedRealtime() + 60 * 1000, pendingIntent);
//                            alarmManager.setRepeating(AlarmManager.RTC, c.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
                        } else {
                            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                        }
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pendingIntent);
            }
        });

    }

    void initView() {
        btnStart = (Button) this.findViewById(R.id.btnStart);
        btnStop = (Button) this.findViewById(R.id.btnStop);
        tvInfo = (TextView) this.findViewById(R.id.tvinfo);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(this, ClockActivity.class);
        pendingIntent = PendingIntent.getActivity(this, requestCode, intent, 0);
    }
}

