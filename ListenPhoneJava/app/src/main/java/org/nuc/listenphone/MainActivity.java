package org.nuc.listenphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.nuc.listenphone.service.ListenService;

public class MainActivity extends AppCompatActivity {

    private Button startButton, stopButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ListenService.class);
                MainActivity.this.startService(intent);
                Toast.makeText(MainActivity.this, "开始监听", Toast.LENGTH_LONG).show();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.stopService(intent);
                Toast.makeText(MainActivity.this, "停止监听", Toast.LENGTH_LONG).show();
            }
        });
    }
}
