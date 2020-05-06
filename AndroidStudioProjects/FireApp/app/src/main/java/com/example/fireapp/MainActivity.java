package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button sendData;
    private EditText nameText, emailText;
    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendData = findViewById(R.id.sendButton);
        nameText = findViewById(R.id.nameTextField);
        emailText = findViewById(R.id.emailTextField);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Name"); //requires child instead of root to retrieve

        //Code for retrieving data
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                nameText.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /* //Code for sending data


        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Create a child in root
                //2. Assign a value to child
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Email",email);
                Log.d(TAG,"Putting Data in firebase");
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Data Added", Toast.LENGTH_LONG).show();
                            emailText.setText("");
                            nameText.setText("");
                        }else{
                            Toast.makeText(MainActivity.this,"Error Adding Data", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });*/
    }
}
