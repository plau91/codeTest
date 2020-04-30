package com.example.codetest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private final static String TAG = "ProfileActivity";

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private EditText profileFirst, profileLast, profileMajor;
    private Button btnUpdate, btnHome;

    private String firstName, lastName, major;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // set text views
        profileFirst = findViewById(R.id.firstName);
        profileLast = findViewById(R.id.lastName);
        profileMajor = findViewById(R.id.major);

        // set button views
        btnUpdate = findViewById(R.id.updateBTN);
        btnHome = findViewById(R.id.homeBTN);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        profileInformation();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }



    private void profileInformation() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (dataSnapshot.getChildrenCount() > 0)
                    {
                        if(map.get("FirstName") != null)
                        {
                            firstName = map.get("FirstName").toString();
                            profileFirst.setText(firstName);
                        }

                        if(map.get("LastName") != null)
                        {
                            lastName = map.get("LastName").toString();
                            profileLast.setText(lastName);
                        }
                        if(map.get("Major") != null)
                        {
                            major = map.get("Major").toString();
                            profileMajor.setText(major);
                        }
                    }
                    else if(dataSnapshot.getChildrenCount() == 0)
                    {
                        firstName = map.get("FirstName").toString();
                        lastName = map.get("LastName").toString();
                        major = map.get("Major").toString();
                        map.put("FirstName", firstName);
                        map.put("LastName", lastName);
                        map.put("Major", major);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error seeing profile");
            }
        });
    }

    private void saveProfile() {
        firstName = profileFirst.getText().toString();
        lastName = profileLast.getText().toString();
        major = profileMajor.getText().toString();

        Map userMap = new HashMap();

        userMap.put("FirstName", firstName);
        userMap.put("LastName", lastName);
        userMap.put("Major", major);

        databaseReference.updateChildren(userMap);
        Toast.makeText(ProfileActivity.this, "Profile created", Toast.LENGTH_LONG).show();

    }


}