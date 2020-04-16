package com.nuc.shoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Shop> shops;
    private GridLayoutManager gridLayoutManager;
    private int cols = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cols == 3) {
                    /**
                     * 根据列数加载不同图标
                     * */
                    imageView.setImageResource(R.mipmap.list2);
                    cols = 1;
                    gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                    itemAdapter = new ItemAdapter(shops, gridLayoutManager);
                    recyclerView.setAdapter(itemAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                } else {
                    imageView.setImageResource(R.mipmap.list);
                    cols = 3;
                    gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                    itemAdapter = new ItemAdapter(shops, gridLayoutManager);
                    recyclerView.setAdapter(itemAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
            }
        });

    }

    private void initView() {
        imageView = findViewById(R.id.img);
        recyclerView = findViewById(R.id.rv);
    }

    private void initData() {
        shops = new ArrayList<>();
        shops.add(new Shop(R.mipmap.c2, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c3, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c5, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c2, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c1, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c2, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c2, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
        shops.add(new Shop(R.mipmap.c4, "休闲", 11, 33));
    }
}
