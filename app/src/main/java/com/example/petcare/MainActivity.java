package com.example.petcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button addPatient, consultation, search, stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPatient = (Button) findViewById(R.id.owner_result_button);
        consultation = (Button) findViewById(R.id.main_consultation);
        search = (Button) findViewById(R.id.cons_addbutton);
        stat = (Button) findViewById(R.id.main_stat);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},100);
        }
        else


        addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPatient.class);
                openIntents(intent);
            }
        });
        consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consultations.class);
                openIntents(intent);
            }
        });

        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DailyStats.class);
                openIntents(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchBy.class);
                openIntents(intent);
            }
        });


    }

    void openIntents(Intent intent) {
        startActivity(intent);
    }
}