package com.duce.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.duce.firebaseauthentication.utils.Tools;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterEmail extends AppCompatActivity {

    private TextView RegisterText;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        RegisterText = findViewById(R.id.RegisterText);
        textInputEditTextEmail = findViewById(R.id.email_edt);
        textInputEditTextPassword = findViewById(R.id.password_edt);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        RegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  signUp();
            }
        });
    }

    private void signUp() {

        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();

        if(textInputEditTextEmail.getText().length() == 0){

            textInputEditTextEmail.setError("Email Required");

        } else if (textInputEditTextPassword.getText().length() == 0){

            textInputEditTextPassword.setError("Password Required");

        }else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Tools.getTools(getApplicationContext()).showToast("Success");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(RegisterEmail.this, EmailPasswordAuth.class));
                                        finish();
                                    }
                                }, 100);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Tools.getTools(getApplicationContext()).showToast(e.getMessage().toString());

                }
            });
        }

    }

}