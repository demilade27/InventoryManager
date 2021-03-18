package com.example.inventorymanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventorymanager.R;

public class PreLogin extends AppCompatActivity {


    //make reference to the buttons in the pre-login view
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_pre_login);

        //get the login button
        loginButton = (Button) findViewById(R.id.pre_login_btn);

        //get the register button
        registerButton = (Button) findViewById(R.id.pre_register_btn);



        //attach a click event to the login butotn
        loginButton.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are about to login", Toast.LENGTH_LONG).show();

                //open the login activity
                //1. Create a new intent
                Intent intent = new Intent(getApplicationContext(), Login.class);

                //Start the activity
                startActivity(intent);

            }
        });

        //attach a click event to the register butotn
        registerButton.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are about to register", Toast.LENGTH_LONG).show();

                //open the login activity
                //1. Create a new intent
                Intent intent = new Intent(getApplicationContext(), Register.class);

                //Start the activity
                startActivity(intent);

            }
        });


    }
}