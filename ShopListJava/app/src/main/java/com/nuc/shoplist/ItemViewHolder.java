package com.nuc.shoplist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 这里是视图绑定类
 * 仅作findviewbyid的工作
 * 把视图id绑定到组件类上
 * */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    TextView info;

    public ItemViewHolder(@NonNull View itemView, int viewType) {
        super(itemView);

        if (viewType == 1) {
            // 一行显示一个商品
            imageView = (ImageView) itemView.findViewById(R.id.img_big);
            title = (TextView) itemView.findViewById(R.id.tittle_big);
            info = (TextView) itemView.findViewById(R.id.tv_info);
        } else {
            imageView = (ImageView) itemView.findViewById(R.id.img_small);
            title = (TextView) itemView.findViewById(R.id.tittle_small);
        }
    }
}
