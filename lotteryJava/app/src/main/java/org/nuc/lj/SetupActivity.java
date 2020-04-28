package org.nuc.lj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetupActivity extends AppCompatActivity {
    private EditText edtName1, edtName2, edtName3, edtName4;//奖项名称
    private EditText edtCount1, edtCount2, edtCount3, edtCount4;
    private Button btnJiang, btnMember;
    private EditText edtMember;
    private RadioGroup radioGroup;
    public static final String TAG = "zhazha";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();

        btnJiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(
                        edtName1.getText().toString(),
                        edtName2.getText().toString(),
                        edtName3.getText().toString(),
                        edtName4.getText().toString(),
                        Integer.parseInt(edtCount1.getText().toString()),
                        Integer.parseInt(edtCount2.getText().toString()),
                        Integer.parseInt(edtCount3.getText().toString()),
                        Integer.parseInt(edtCount4.getText().toString())
                );
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radedit:
                        edtMember.setText("");
                        break;
                    case R.id.radimport:
                        break;
                }
            }
        });

        btnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtMember.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String filename = simpleDateFormat.format(calendar.getTime()) + ".txt";
                writeFileToSD(content, filename);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //初始化界面对象
    void initView() {
        edtName1 = this.findViewById(R.id.edtname1);
        edtName2 = this.findViewById(R.id.edtname2);
        edtName3 = this.findViewById(R.id.edtname3);
        edtName4 = this.findViewById(R.id.edtname4);
        edtCount1 = this.findViewById(R.id.edtcount1);
        edtCount2 = this.findViewById(R.id.edtcount2);
        edtCount3 = this.findViewById(R.id.edtcount3);
        edtCount4 = this.findViewById(R.id.edtcount4);
        btnJiang = this.findViewById(R.id.btnjiang);
        btnMember = this.findViewById(R.id.btnmember);
        edtMember = this.findViewById(R.id.edtmember);
        radioGroup = this.findViewById(R.id.radgroup);
    }

    public void save(String n1, String n2, String n3, String n4,
                     int c1, int c2, int c3, int c4) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("rewardconfig",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("n1", n1);
        editor.putInt("c1", c1);
        editor.putString("n2", n2);
        editor.putInt("c2", c2);
        editor.putString("n3", n3);
        editor.putInt("c3", c3);
        editor.putString("n4", n4);
        editor.putInt("c4", c4);
        editor.commit();
    }

    public void writeFileToSD(String content, String filename) {
        String sdStatus = Environment.getExternalStorageState();
        Log.d(TAG, sdStatus);
        if (sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            String sdRoot = Environment.getExternalStorageDirectory().toString();
            String path = sdRoot + File.separator + "rewardpath";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
                Log.d(TAG, file.getAbsolutePath());
            }

            File newFile = new File(file, filename);

            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(newFile, false);
                fileOutputStream.write(content.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }
        }
    }
}
