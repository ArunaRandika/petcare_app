package com.example.petcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchByDate extends AppCompatActivity {
private CalendarView calender;
    RecyclerView recyclerView;
    private DataBaseHelper db = new DataBaseHelper(this);
    private ArrayList<String> mainText, secondText, diagnosis, prescription, date, telephone, zero;
    private Button searchButton;
    private EditText searchBar;
    ResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_date);
        calender=(CalendarView)findViewById(R.id.search_date_calender);
        mainText = new ArrayList<>();
        secondText = new ArrayList<>();
        diagnosis = new ArrayList<>();
        prescription = new ArrayList<>();
        date = new ArrayList<>();
        telephone = new ArrayList<>();
        zero = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycledate);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mainText.clear();
                secondText.clear();
                diagnosis.clear();
                prescription.clear();
                date.clear();
                telephone.clear();

                String sMonth = "";
                String sDay="";
                String sYear=String.valueOf(year);
                if (month < 10 ) {
                    sMonth = "0"+String.valueOf(month+1);

                }if( dayOfMonth<10){
                    sDay="0"+String.valueOf(dayOfMonth);
                } else {
                    sMonth=String.valueOf(month);
                    sDay=String.valueOf(dayOfMonth);
                }

                String sDate=sYear+"/"+sMonth+"/"+sDay;
                storeDataIntoArrays(sDate);
                adapter = new ResultAdapter(SearchByDate.this, mainText, secondText, date, prescription, diagnosis, telephone, SearchByDate.this);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void storeDataIntoArrays(String searchEnter) {
        Cursor cursor = db.consultationData("owner");
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Inside the Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                String cursorString = cursor.getString(4);
                if (cursorString.contains(searchEnter)) {

                    mainText.add(cursor.getString(9));
                    secondText.add(cursor.getString(5));
                    telephone.add(cursor.getString(10));
                    diagnosis.add(cursor.getString(3));
                    prescription.add(cursor.getString(2));
                    date.add(cursor.getString(4));


                }


            }
        }
    }
}