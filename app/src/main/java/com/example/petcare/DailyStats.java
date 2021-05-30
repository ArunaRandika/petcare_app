package com.example.petcare;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class DailyStats extends AppCompatActivity {
    private TextView dailyCases, totalIncome;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    private DataBaseHelper dbHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stats);
        dailyCases = (TextView) findViewById(R.id.stats_cases);
        totalIncome = (TextView) findViewById(R.id.stats_total);

        addData();

    }

    private void addData() {
        Cursor cursor = dbHelper.incomeData();
        int income=0;
        int cases=0;
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Inside the Database", Toast.LENGTH_SHORT).show();
        } else {
            //loading data from the database from the array
            while (cursor.moveToNext()) {

                if (cursor.getString(1).equals(formatter.format(date))) {
                    income += Integer.parseInt(cursor.getString(2));
                    cases++;
                }
            }
            totalIncome.setText(income+" LKR");
            dailyCases.setText(""+cases);
        }
    }
}