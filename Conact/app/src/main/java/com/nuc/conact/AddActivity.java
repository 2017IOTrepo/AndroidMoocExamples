package com.nuc.conact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends Activity implements View.OnClickListener {
    private ImageView returnImageView;
    private ImageView leftImageView;
    private ImageView rightImageView;
    private ImageView selectImageView;
    private EditText nameEditText;
    private EditText telEditText;
    private Button resetButton;
    private Button addAndContinueButton;

    private List<Person> personList;

    private int index = 0;
    private int imgIds[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        // 数据初始化不要跟视图混用
        personList = new ArrayList<>();
    }


    /**
     * 初始化试图
     */
    private void initView() {
        returnImageView = findViewById(R.id.returnImageView);
        leftImageView = findViewById(R.id.leftImageView);
        rightImageView = findViewById(R.id.rightImageView);
        selectImageView = findViewById(R.id.selectImageView);
        nameEditText = findViewById(R.id.nameEditText);
        telEditText = findViewById(R.id.telEditText);
        resetButton = findViewById(R.id.resetButton);
        addAndContinueButton = findViewById(R.id.addAndContinueButton);

        returnImageView.setOnClickListener(this);
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        addAndContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnImageView:
                Intent intent = getIntent();
                intent.putExtra("persons", (Serializable) personList);
                setResult(0, intent);
                finish();
                break;
            case R.id.leftImageView:
                index = index - 1 == -1 ? imgIds.length - 1 : index - 1;
                selectImageView.setImageResource(imgIds[index]);
                break;

            case R.id.rightImageView:
                index = index + 1 == imgIds.length ? 0 : index + 1;
                selectImageView.setImageResource(imgIds[index]);
                break;
            case R.id.addAndContinueButton:
                personList.add(new Person(
                        nameEditText.getText().toString(),
                        telEditText.getText().toString(),
                        imgIds[index]
                ));
                break;

            case R.id.resetButton:
                nameEditText.setText("");
                telEditText.setText("");
                selectImageView.setImageResource(imgIds[0]);
                index = 0;
                break;
        }
    }
}
