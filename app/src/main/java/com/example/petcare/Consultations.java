package com.example.petcare;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Consultations extends AppCompatActivity {

    private Button addButton;
    private EditText idSearch, petName, diagnosis, prescription, dateAdded,age,weight,charges;
    DataBaseHelper db = new DataBaseHelper(this);
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    String owner,telephone;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultations);

        addButton = (Button) findViewById(R.id.cons_addbutton);
        idSearch = (EditText) findViewById(R.id.cons_id);
        petName = (EditText) findViewById(R.id.cons_petname);
        dateAdded = (EditText) findViewById(R.id.cons_date);
        diagnosis = (EditText) findViewById(R.id.cons_diagnosis);
        prescription = (EditText) findViewById(R.id.cons_prescription);
        age = (EditText) findViewById(R.id.cons_age);
        weight = (EditText) findViewById(R.id.cons_weight);
        charges = (EditText) findViewById(R.id.cons_charges);

        dateAdded.setEnabled(false);
        addButton.setEnabled(false);
        petName.setEnabled(false);

            dateAdded.setText(""+formatter.format(date));


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String diagnosisString=stringConverter(diagnosis);
                String prescriptionString=stringConverter(prescription);
                String chargesString=charges.getText().toString();
                if(!(editTextChecker(idSearch) || editTextChecker(age) ||  editTextChecker(weight) )){
                    if(editTextChecker(diagnosis)){
                        diagnosisString="None";

                    }
                    if (editTextChecker(prescription)) {
                        prescriptionString="None";
                    }if(editTextChecker(charges)){
                        chargesString="0";
                    }

                    if(db.addConsultation(stringConverter(idSearch),prescriptionString,diagnosisString,stringConverter(petName),stringConverter(age),stringConverter(dateAdded),chargesString,stringConverter(weight),owner,telephone)){
                        idSearch.setText("");age.setText("");weight.setText("");petName.setText("");prescription.setText("");diagnosis.setText("");charges.setText("");
                        db.addIncome(stringConverter(dateAdded),chargesString);

                    }


                }
            }
        });
        idSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!idSearch.getText().toString().equals("")) {
                    idChecker(idSearch.getText().toString());

                } else {
                    idSearch.getBackground().setColorFilter(GREEN,
                            PorterDuff.Mode.SRC_ATOP);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    void idChecker(String entry) {
        Cursor cursor = db.idChecker(entry);
        if (entry != " ") {
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                petName.setText("");
                idSearch.getBackground().setColorFilter(RED,
                        PorterDuff.Mode.SRC_ATOP);
                addButton.setEnabled(false);
            } else {
                while (cursor.moveToNext()) {
                    petName.setText(cursor.getString(4));
                    owner=cursor.getString(1);
                    telephone=cursor.getString(2);
                    idSearch.getBackground().setColorFilter(GREEN,
                            PorterDuff.Mode.SRC_ATOP);
                    addButton.setEnabled(true);
                }
            }
        }


    }

    boolean editTextChecker(EditText text){
        if(text.getText().toString().equals("")){
            return true;
        }
        return false;
    }
    String stringConverter(EditText text){
        return text.getText().toString();
    }
}