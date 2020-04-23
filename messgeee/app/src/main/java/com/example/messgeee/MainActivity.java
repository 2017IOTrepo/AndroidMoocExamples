package com.example.messgeee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText edtdate,edttime,edtcontent,edtnum;
    private Button btn;
    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        initView();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edtdate.getText().toString();
                String dates[] = date.split("-");
                int year = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int day = Integer.parseInt(dates[2]);
                String time = edttime.getText().toString();
                String times[] = time.split(":");
                int hour = Integer.parseInt(times[0]);
                int minute = Integer.parseInt(times[1]);
                calendar.set(year,month-1,day);
                String num = edtnum.getText().toString();
                String content = edtcontent.getText().toString();
                AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this,SmsReceiver.class);
                intent.putExtra("content",content);
                intent.putExtra("num",num);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    manager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),1000*60*60*24,pendingIntent);
                }else {
                    manager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
                }
            }
        });
    }

    void initView(){
        edtcontent = (EditText)this.findViewById(R.id.edtcontent);
        edtdate = (EditText)this.findViewById(R.id.edtdate);
        edttime = (EditText)this.findViewById(R.id.edttime);
        edtnum= (EditText)this.findViewById(R.id.edtnum);
        btn = (Button)this.findViewById(R.id.btnsend);
    }
}

