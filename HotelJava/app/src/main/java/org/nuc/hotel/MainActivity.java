package org.nuc.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtCity, edtIndate, edtPrice;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getApplication().getDrawable(R.mipmap.hotle1);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setTitle("");

        initView();
        //选择城市
        edtCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, edtCity);
                popupMenu.getMenuInflater().inflate(R.menu.menu_po, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.wuhan:
                                edtCity.setText("济南");
                                break;
                            case R.id.ez:
                                edtCity.setText("烟台");
                                break;
                            case R.id.jm:
                                edtCity.setText("青岛");
                                break;
                            case R.id.cs:
                                edtCity.setText("长沙");
                                break;
                            case R.id.cd:
                                edtCity.setText("常德");
                                break;
                            case R.id.hy:
                                edtCity.setText("衡阳");
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //选择进店日期
        edtIndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtIndate.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    }
                }, 2020, 04, 14);
                datePickerDialog.show();
            }
        });

        //选择价格区间
        edtPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this, R.style.myDiaStyle);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.price_layout, null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.y = 20;
                window.setAttributes(layoutParams);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "天气").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 1, 0, "公交").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 2, 0, "自驾").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(MainActivity.this, "天气", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(MainActivity.this, "公交", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "自驾", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void initView() {
        edtCity = this.findViewById(R.id.edtcity);
        edtIndate = this.findViewById(R.id.edtindate);
        edtPrice = this.findViewById(R.id.edtprice);
    }
}