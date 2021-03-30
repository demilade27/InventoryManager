package com.example.inventorymanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventorymanager.R;

public class Login extends AppCompatActivity {

    TextView registerRequest;
    EditText email, password;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //link the components
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login_btn);
        registerRequest = (TextView) findViewById(R.id.reg_req);

        //when the log in button is clicked
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyPassword(email, password) == true) {
                    //go to the main activity which is the homepage
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();

            }
        });

        //add a aclick action to it
        registerRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create a new intent
                Intent intent = new Intent(getApplicationContext(), Register.class);
                //start the activity
                startActivity(intent);
            }
        });
    }

    private boolean verifyPassword(EditText email, EditText password) {
        String stringEmail;
        String stringPassword;
        stringEmail = email.getText().toString();
        stringPassword = password.getText().toString();
        //check if the inputs are not empty
        if (!stringEmail.equals("") && !stringPassword.equals("")) {

            return true;
        }
        return false;
    }
}
