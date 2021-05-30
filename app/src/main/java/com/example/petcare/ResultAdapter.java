package com.example.petcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> mainText, secondText, date, prescription, diagnosis, telephone;
    Activity activity;


    public ResultAdapter(Context context, ArrayList<String> mainText, ArrayList<String> secondText, ArrayList<String> date, ArrayList<String> prescription, ArrayList<String> diagnosis, ArrayList<String> telephone, Activity activity) {
        this.context = context;
        this.mainText = mainText;
        this.secondText = secondText;
        this.date = date;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.telephone = telephone;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.data_row,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.dateText.setText(String.valueOf(date.get(position)));
        holder.mainTextView.setText(String.valueOf(mainText.get(position)));
        holder.secondTextView.setText(String.valueOf(secondText.get(position)));
        holder.prescriptionText.setText(String.valueOf(prescription.get(position)));
        holder.diagnosisText.setText(String.valueOf(diagnosis.get(position)));
        holder.telephoneText.setText(String.valueOf(telephone.get(position)));

        holder.telephoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +holder.telephoneText.getText().toString() ));
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainText.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mainTextView, secondTextView, prescriptionText, diagnosisText, dateText,telephoneText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainTextView=itemView.findViewById(R.id.row_maintext);
            secondTextView = itemView.findViewById(R.id.row_secondary);
            prescriptionText=itemView.findViewById(R.id.row_prescription);
            diagnosisText=itemView.findViewById(R.id.row_diagnosis);
            dateText=itemView.findViewById(R.id.row_date);
            telephoneText=itemView.findViewById(R.id.row_telephone);

        }
    }

}
