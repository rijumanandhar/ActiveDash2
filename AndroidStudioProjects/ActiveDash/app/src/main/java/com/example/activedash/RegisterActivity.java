package com.example.activedash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = RegisterActivity.class.getSimpleName();

    private Button uploadPicBtn, signUpBtn;
    private EditText nameText, usernameText,emailText, passwordText, cPasswordText, dobText;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        repository = new Repository();

        progressDialog = new ProgressDialog(this);

        uploadPicBtn = findViewById(R.id.uploadButton);
        signUpBtn = findViewById(R.id.signUpButton);

        nameText = findViewById(R.id.nameText);
        usernameText = findViewById(R.id.userNameText);
        emailText = findViewById(R.id.emailText);
        passwordText =  findViewById(R.id.passwordText);
        cPasswordText = findViewById(R.id.cPasswordText);
        dobText = findViewById(R.id.dobText);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignUp();
            }
        });

    }

    public void startSignUp(){
        final String name = nameText.getText().toString().trim();
        final String username = usernameText.getText().toString().trim();
        final String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String cPassword = cPasswordText.getText().toString().trim();
        final String dob = dobText.getText().toString().trim();

        //validation
        if (validate() == "success"){
            Log.d(TAG,"validate vitra");
            progressDialog.setMessage("Signing Up");
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String user_id = mAuth.getCurrentUser().getUid();
                    Log.d(TAG,"user_id "+user_id);
                    repository.insertUserData(user_id,name,email,username,dob,"default");
                    progressDialog.dismiss();

                    Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }
            });

        }

    }

    public String validate (){
        return "success";
    }
}
