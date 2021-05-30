package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchBy extends AppCompatActivity {
private Button byOwner,byPet,byDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by);
        byOwner=(Button)findViewById(R.id.owner_result_button);
        byPet=(Button)findViewById(R.id.search_petname);
        byDate=(Button)findViewById(R.id.search_date);

        byOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchBy.this, SearchByOwnerResults.class);
                startActivity(intent);
            }
        });

        byPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchBy.this,SearchByPetName.class);
                startActivity(intent);
            }
        });

        byDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchBy.this,SearchByDate.class);
                startActivity(intent);
            }
        });


    }
}