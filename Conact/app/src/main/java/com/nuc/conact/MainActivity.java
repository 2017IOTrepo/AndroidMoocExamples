package com.nuc.conact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private List<Person> personList;
    private ListView listView;
    private MainListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.listView);
        personList = new ArrayList<>();
        adapter = new MainListViewAdapter(this, R.layout.item, personList);

        listView.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 去重代码
     */
    private void deduplicate() {
        personList = personList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<Person>(comparing(Person::getName))), ArrayList::new)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Person> p;
        if (resultCode == 0) {
            p = (List<Person>) data.getSerializableExtra("persons");
            personList.addAll(p);
            deduplicate();
            adapter.clear();
            adapter.addAll(personList);
            adapter.notifyDataSetChanged();
        }
    }
}
