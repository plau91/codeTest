package com.example.codetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity {

    Button btnSignIn;
    TextView btnRegister;
    EditText emailID, passwordID;

    private DatabaseReference databaseReference;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //STEP1: firebase setup
        // create a Firebase instance & reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();

        firebaseListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        };

        //STEP 2:  setup of login/password gui ojects
        // grab objects for email and password EditTexts
        emailID = findViewById(R.id.emailEnter);
        passwordID = findViewById(R.id.passwordEnter);
        btnSignIn = findViewById(R.id.signInBTN);
        btnRegister = findViewById(R.id.registerBTN);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
            }
        });

    }

    private void login() {
        final String email = emailID.getText().toString();
        final String password = passwordID.getText().toString();

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // if email & password is correct: login to home page
                if(task.isSuccessful()) {

                    FirebaseUser user = fAuth.getCurrentUser();
                    String s = "Welcome back " + user.getEmail();
                    Toast.makeText(LogInActivity.this, s, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                }
                // if not correct: error message to re try
                else {
                    Toast.makeText(LogInActivity.this, "Sign in failed.", Toast.LENGTH_SHORT).show();
                    // updateUI(null);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(firebaseListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fAuth.removeAuthStateListener(firebaseListener);
    }

}