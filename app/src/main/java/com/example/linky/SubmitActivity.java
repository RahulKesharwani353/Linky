package com.example.linky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SubmitActivity extends AppCompatActivity {

    Button submit;
    TextInputEditText name,email,phnNo,description,location,noOfPeople;
    DatabaseReference reqRef;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        submit = findViewById(R.id.addProduct_btn);

        progressDialog = new ProgressDialog(this);
        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        phnNo = findViewById(R.id.phnNo);
        description = findViewById(R.id.Description);
        location = findViewById(R.id.Location);
        noOfPeople = findViewById(R.id.People);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reqRef= database.getReference().child("Requests");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHelp();
            }
        });
    }

    private void addHelp() {

        if(name.getText().toString().equals("")||phnNo.getText().toString().equals("")||email.getText().toString().equals("")||
                description.getText().toString().equals("")||location.getText().toString().equals("")||noOfPeople.getText().toString().equals(""))
        {
            Toast.makeText(this, "please enter all details", Toast.LENGTH_SHORT).show();
        }
        else
        storeInfo();

    }

    private void storeInfo() {
        progressDialog.setTitle("Adding Request");
        progressDialog.setMessage("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String saveCurrentDate =
                new SimpleDateFormat("MM dd yyyy ").format(Calendar.getInstance().getTime());
       String saveCurrentTime=
                new SimpleDateFormat("HH:mm:ss a").format(Calendar.getInstance().getTime());
       String randomRequestId = saveCurrentDate + saveCurrentTime;


        HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("ReqId", randomRequestId);
        profileMap.put("nameOfApplicant", name.getText().toString());
        profileMap.put("email", email.getText().toString());
        profileMap.put("phone", phnNo.getText().toString());
        profileMap.put("location", location.getText().toString());
        profileMap.put("description", description.getText().toString());
        profileMap.put("peoples", noOfPeople.getText().toString());
        reqRef.child(randomRequestId).
                updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SubmitActivity.this, "New Request Added Successfully..!!!", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    startActivity(new Intent(SubmitActivity.this,Dashboard.class));
                    finishAffinity();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}