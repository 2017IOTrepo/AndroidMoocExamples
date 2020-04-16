package com.nuc.conact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class MainListViewAdapter extends ArrayAdapter<Person> {

    public MainListViewAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Person person = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageItem);
        imageView.setImageResource(person.getImgId());
        TextView textView = convertView.findViewById(R.id.nameTextView);
        textView.setText(person.getName());
        TextView textView1 = convertView.findViewById(R.id.telTextView);
        textView1.setText(person.getTel());

        return convertView;
    }
}
