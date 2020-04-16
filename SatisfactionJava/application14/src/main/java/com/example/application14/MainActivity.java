package com.example.application14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText ZyEdit, SexEdit, YearEdit, workEdit, schoolEdit;
    private String zhuanye[] = {"计科", "物联网", "大数据"};
    private String year[] = {"2019", "2018", "2017", "2016"};
    private int yearId;
    private String[] work = {"国家行政企业", "公私合作企业", "中外合资企业"};
    private boolean initChoiceSets[] = {false, false, false};
    private Button btnOk;
    int progress = 0;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                SexEdit.setText("男");
                break;
            case 1:
                SexEdit.setText("女");
                break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("性别");
        menu.add(0, 0, 0, "男");
        menu.add(0, 1, 1, "女");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        initView();
        ZyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择专业");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setItems(zhuanye, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ZyEdit.setText(zhuanye[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        YearEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择入学年份");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setSingleChoiceItems(year, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yearId = which;

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        YearEdit.setText(year[yearId]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        workEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择工作过的单位性质");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMultiChoiceItems(work, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        initChoiceSets[which] = isChecked;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temp = "";
                        for (int i = 0; i < initChoiceSets.length; i++) {
                            if (initChoiceSets[i]) {
                                temp = temp + work[i] + ",";
                            }
                        }
                        workEdit.setText(temp);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        schoolEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View view = inflater.inflate(R.layout.dialog_select, null);
                final RadioButton radKnow = (RadioButton) view.findViewById(R.id.radknow);
                final RadioButton radskill = (RadioButton) view.findViewById(R.id.radskill);
                final RadioButton radjyan = (RadioButton) view.findViewById(R.id.radjyan);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("最大收获");
                builder.setMessage("请选择在校期间的收获或在其他栏填写");
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (radKnow.isChecked()) {
                            Toast.makeText(MainActivity.this, "知识", Toast.LENGTH_SHORT).show();
                        }
                        if (radjyan.isChecked()) {
                            Toast.makeText(MainActivity.this, "经验", Toast.LENGTH_SHORT).show();
                        }
                        if (radskill.isChecked()) {
                            Toast.makeText(MainActivity.this, "技能", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread() {
                    public void run() {
                        while (progress < progressDialog.getMax()) {
                            progressDialog.setProgress(progress);
                            progress++;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });

        this.registerForContextMenu(SexEdit);
    }

    void initView() {
        ZyEdit = (EditText) this.findViewById(R.id.edtspecial);
        SexEdit = (EditText) this.findViewById(R.id.edtsex);
        YearEdit = (EditText) this.findViewById(R.id.edtyear);
        workEdit = (EditText) this.findViewById(R.id.edtdanwei);
        schoolEdit = (EditText) this.findViewById(R.id.edtshouhuo);
        btnOk = (Button) this.findViewById(R.id.btok);
    }
}
