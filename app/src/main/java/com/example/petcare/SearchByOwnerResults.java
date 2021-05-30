package com.example.petcare;

import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.graphics.Color.GREEN;

public class SearchByOwnerResults extends AppCompatActivity {
    RecyclerView recyclerView;
    private DataBaseHelper db = new DataBaseHelper(this);
    private ArrayList<String> mainText, secondText, diagnosis, prescription, date, telephone, zero;
    private Button searchButton;
    private EditText searchBar;
    ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_onwer_results);
        mainText = new ArrayList<>();
        secondText = new ArrayList<>();
        diagnosis = new ArrayList<>();
        prescription = new ArrayList<>();
        date = new ArrayList<>();
        telephone = new ArrayList<>();
        zero = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        searchBar = (EditText) findViewById(R.id.owner_result_searchbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchByOwnerResults.this));
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mainText.clear();
                secondText.clear();
                diagnosis.clear();
                prescription.clear();
                date.clear();
                telephone.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!searchBar.getText().toString().equals("")) {
                    storeDataIntoArrays(searchBar.getText().toString());

                } else {
                    searchBar.getBackground().setColorFilter(GREEN,
                            PorterDuff.Mode.SRC_ATOP);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter = new ResultAdapter(SearchByOwnerResults.this, mainText, secondText, date, prescription, diagnosis, telephone, SearchByOwnerResults.this);
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
                System.out.println(searchEnter);
                String cursorString = cursor.getString(9).toLowerCase();
                if (cursorString.contains(searchEnter.toLowerCase())) {
                    System.out.println(cursor.getString(9));
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