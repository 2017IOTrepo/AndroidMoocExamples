package com.nuc.shoplist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recyclerview适配器
 * */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Shop> shops;
    private GridLayoutManager gridLayoutManager;

    public ItemAdapter(List<Shop> shops, GridLayoutManager gridLayoutManager) {
        this.shops = shops;
        this.gridLayoutManager = gridLayoutManager;
    }

    /**
     * 当视图创建的时候构建 用于根据不用viewType创建不同视图
     * */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multi, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    /**
     * 数据绑定类 相当于把数据绑定到视图中来
     * 这里直接调用是因为onCreateViewHolder里面已经实例化了ItemViewHolder
     * */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Shop shop = shops.get(position % 4);
        holder.title.setText(shop.getTitle());
        holder.imageView.setImageResource(shop.getImgResId());
        /**
         * 这里需要按需加载 因为多列模式是没有信息的
         * */
        if (getItemViewType(position) == 1) {
            holder.info.setText("感兴趣" + shop.getLikes() + "人," + shop.getComments() + "个评论.");
        }
    }

    /**
     * 这里是返回recyclerview里面的物品数
     * */
    @Override
    public int getItemCount() {
        return shops.size();
    }

    public int getItemViewType(int pos) {
        if (gridLayoutManager.getSpanCount() == 1) {
            return 1;
        }
        return 3;
    }
}
