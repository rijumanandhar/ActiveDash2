package com.example.activedash;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Repository {
    private DatabaseReference user;

    Repository(){
        user = FirebaseDatabase.getInstance().getReference().child("user");
    }

    public void insertUserData(String uid,String name, String email, String username, String dob, String picture){
        DatabaseReference mRef = user.child(uid);
        mRef.child("name").setValue(name);
        mRef.child("username").setValue(username);
        mRef.child("email").setValue(email);
        mRef.child("dob").setValue(dob);
        mRef.child("picture").setValue(picture);
    }
}
