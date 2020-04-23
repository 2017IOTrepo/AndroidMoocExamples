package com.example.messgeee;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class SmsReceiver extends BroadcastReceiver {
    String receiverNum = "";
    String content = "";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        receiverNum = intent.getStringExtra("num");
        content = intent.getStringExtra("content");
        if (receiverNum.length() !=0){
            //发送短信
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(receiverNum,null,content,null,null);
        }
        Toast.makeText(context,"短信发送成功",Toast.LENGTH_SHORT).show();
        //在状态栏给出通知消息
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker("收到信息");
        builder.setContentTitle("提醒");
        builder.setContentTitle("短信已经发送");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.build();
        manager.notify(1,notification);
    }
}
