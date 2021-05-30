package com.example.petcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPatient extends AppCompatActivity {

    private Button addButton;
    private EditText owner, telephone, address, petname, id;
    DataBaseHelper dbHelper = new DataBaseHelper(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        id = (EditText) findViewById(R.id.ad_patentId);
        id.setEnabled(false);
        owner = (EditText) findViewById(R.id.add_ownername);
        telephone = (EditText) findViewById(R.id.add_ownertp);
        petname = (EditText) findViewById(R.id.add_petname);
        address = (EditText) findViewById(R.id.owner_address);
        id();
        addButton = (Button) findViewById(R.id.cons_addbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(owner.getText().toString().equals("") || telephone.getText().toString().equals("") || petname.getText().toString().equals("") || address.getText().toString().equals(""))) {
                    if (!(telephone.getText().toString().length() < 10)) {
                        if (!(owner.getText().toString().matches(".*\\d.*") || petname.getText().toString().matches(".*\\d.*"))) {
                            if(dbHelper.addPatient(owner.getText().toString(), telephone.getText().toString(), address.getText().toString(), petname.getText().toString())){
                                owner.setText("");
                                petname.setText("");
                                telephone.setText("");
                                address.setText("");
                                id();

                            }
                        } else {
                            toast("Names cannot contain numbers!");
                        }
                    } else {
                        toast("Telephone number length is not valid");
                    }
                } else {
                    toast("Please fill all the Fields");
                }
            }
        });
    }

    void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    void id(){
        int rowId= dbHelper.getId()+1;
        if (String.valueOf(dbHelper.getId()).length() == 1) {
            id.setText("PCNS000" +rowId);
        } else if (String.valueOf(dbHelper.getId()).length() == 2) {
            id.setText("PCNS00" + dbHelper.getId()+rowId);
        } else if (String.valueOf(dbHelper.getId()).length() == 3) {
            id.setText("PCNS0" + dbHelper.getId()+rowId);
        } else if (String.valueOf(dbHelper.getId()).length() == 4) {
            id.setText(dbHelper.getId());
        }

    }
}