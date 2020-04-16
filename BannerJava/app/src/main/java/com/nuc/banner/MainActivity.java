package com.nuc.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 这里视频里分了两部分
         * 第一个setContentView里面是第一部分的
         * 第二个setContentView是第二部分的
         * 截图的话把要么第一个注释掉跑一遍 要么第二个注释掉跑一遍
         * */
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.updown);

        viewFlipper = findViewById(R.id.viewflipper);

        /**
         * 下面注释掉的是第三部分动态加载的代码
         * 需要截图取消注释就可以
         * */
//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.chun);
//        viewFlipper.addView(imageView);

        viewFlipper.startFlipping();
    }
}
