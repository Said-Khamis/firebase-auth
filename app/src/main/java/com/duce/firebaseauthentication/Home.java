package com.duce.firebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button buttonlogout;
    private TextView textViewName , textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       buttonlogout = findViewById(R.id.logout);

       firebaseAuth = FirebaseAuth.getInstance();

       textViewEmail = findViewById(R.id.email);
       textViewName = findViewById(R.id.displayName);


       buttonlogout.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
             firebaseAuth.signOut();
             startActivity(new Intent(Home.this,EmailPasswordAuth.class));
             finish();
           }
       });

       getUserInfo();
    }

    private void getUserInfo() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
             String email = firebaseUser.getEmail();
             String dName = firebaseUser.getDisplayName();

             textViewName.setText(dName);
             textViewEmail.setText(email);
        }

    }
}