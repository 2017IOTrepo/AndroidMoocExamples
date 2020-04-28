package org.nuc.lj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvj1, tvj2, tvj3, tvj4;//奖项名称和奖项数显示
    private TextView tvj1content, tvj2content, tvj3content, tvj4content;//每个奖项中奖名单
    private TextView tvInfo;
    private Button btnStart;
    private ArrayList<String> jiangMember = new ArrayList<>();
    private int userId = 0;
    boolean flag = false;
    private int userCount = 0;
    private int[] rewardCount = new int[4];

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //产生随机名单的index
            userId = (int) (Math.random() * jiangMember.size());
            tvInfo.setText(jiangMember.get(userId));
            super.handleMessage(msg);
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (flag) {
                try {
                    Thread.sleep(100);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        read();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String filename = simpleDateFormat.format(calendar.getTime()) + ".txt";
        final String[] temp = readFilesFromSDCard(filename).split(",");
        for (int i = 0; i < temp.length; i++) {
            jiangMember.add(temp[i]);
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    btnStart.setText("开始");
                    userCount++;

                    if (userCount <= rewardCount[0]) {
                        tvj1content.setText(
                                tvj1content.getText().length() == 0 ?
                                        jiangMember.remove(userId) :
                                        tvj1content.getText().toString() + ", " + jiangMember.remove(userId)
                        );
                    } else if (userCount <= rewardCount[0] + rewardCount[1]) {
                        tvj2content.setText(
                                tvj2content.getText().length() == 0 ?
                                        jiangMember.remove(userId) :
                                        tvj2content.getText().toString() + ", " + jiangMember.remove(userId)
                        );
                    } else if (userCount <= rewardCount[0] + rewardCount[1] + rewardCount[2]) {
                        tvj3content.setText(
                                tvj3content.getText().length() == 0 ?
                                        jiangMember.remove(userId) :
                                        tvj3content.getText().toString() + ", " + jiangMember.remove(userId)
                        );
                    } else if (userCount <= rewardCount[0] + rewardCount[1] + rewardCount[2] + rewardCount[3]) {
                        tvj4content.setText(
                                tvj4content.getText().length() == 0 ?
                                        jiangMember.remove(userId) :
                                        tvj4content.getText().toString() + ", " + jiangMember.remove(userId)
                        );
                    }


                } else {
                    flag = true;
                    btnStart.setText("停止");
                    Thread thread = new Thread(runnable);
                    thread.start();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "查看");
        menu.add(0, 1, 0, "保存");
        menu.add(0, 2, 0, "设置");
        menu.add(0, 3, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    //初始化组件
    void initView() {
        tvj1 = this.findViewById(R.id.tvj1);
        tvj2 = this.findViewById(R.id.tvj2);
        tvj3 = this.findViewById(R.id.tvj3);
        tvj4 = this.findViewById(R.id.tvj4);
        tvj1content = this.findViewById(R.id.tvj1content);
        tvj2content = this.findViewById(R.id.tvj2content);
        tvj3content = this.findViewById(R.id.tvj3content);
        tvj4content = this.findViewById(R.id.tvj4content);
        tvInfo = this.findViewById(R.id.tvinfo);
        btnStart = this.findViewById(R.id.btnStart);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent1 = new Intent(this, ViewActivity.class);
                this.startActivity(intent1);
                break;
            case 1:
                break;
            case 2:
                Intent intent = new Intent(this, SetupActivity.class);
                this.startActivity(intent);
                break;
            case 3:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void read() {
        SharedPreferences sharedPreferences = getSharedPreferences("rewardconfig", MODE_PRIVATE);
        String n1 = sharedPreferences.getString("n1", "");
        String n2 = sharedPreferences.getString("n2", "");
        String n3 = sharedPreferences.getString("n3", "");
        String n4 = sharedPreferences.getString("n4", "");
        int c1 = sharedPreferences.getInt("c1", 0);
        int c2 = sharedPreferences.getInt("c2", 0);
        int c3 = sharedPreferences.getInt("c3", 0);
        int c4 = sharedPreferences.getInt("c4", 0);
        if (n1.equals("")) {
            Toast.makeText(MainActivity.this, "请先设置奖项", Toast.LENGTH_SHORT).show();
            return;
        }
        rewardCount[0] = c1;
        rewardCount[1] = c2;
        rewardCount[2] = c3;
        rewardCount[3] = c4;
        tvj1.setText(n1 + "(" + c1 + ")");
        tvj2.setText(n2 + "(" + c2 + ")");
        tvj3.setText(n3 + "(" + c3 + ")");
        tvj4.setText(n4 + "(" + c4 + ")");
    }

    public String readFilesFromSDCard(String fileName) {
        String content = "";
        FileInputStream fileInputStream = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String sdCardPath = Environment.getExternalStorageDirectory().toString();
            String tempPath = sdCardPath + File.separator + "rewardpath";
            try {
                File newFile = new File(tempPath, fileName);
                fileInputStream = new FileInputStream(newFile);
                int length = fileInputStream.available();
                byte[] buffer = new byte[length];
                fileInputStream.read(buffer);
                content = new String(buffer);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
