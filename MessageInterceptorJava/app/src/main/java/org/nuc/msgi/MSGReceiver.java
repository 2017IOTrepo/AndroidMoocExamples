package org.nuc.msgi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MSGReceiver extends BroadcastReceiver {
    private String tel = "";
    private String content = "";

    public MSGReceiver(String tel, String content) {
        this.tel = tel;
        this.content = content;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //当接收到的是指定的电话号码，要给出拦截信息
        //当接收到的是指定的敏感词汇，要给出拦截信息
        StringBuilder number = new StringBuilder();//存储短信号码
        StringBuilder body = new StringBuilder();//存储短信内容
        Bundle bundle = intent.getExtras();//短信相关信息
        if (bundle != null) {
            Object[] objects = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[objects.length];//封装短信格式
            for (int i = 0; i < objects.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
            for (SmsMessage message : messages) {
                number.append(message.getDisplayOriginatingAddress());//电话号码
                body.append(message.getDisplayMessageBody());//短信内容
            }
            String smsnum = number.toString();
            String smsbody = body.toString();
            if (smsnum.equals(tel)) {
                Toast.makeText(context, "你的电话号码被拦截！", Toast.LENGTH_SHORT).show();
                this.abortBroadcast();
            }

            if (smsbody.equals(content)) {
                Toast.makeText(context, "你的短信信息被拦截！", Toast.LENGTH_SHORT).show();
                this.abortBroadcast();
            }
        }
    }
}
