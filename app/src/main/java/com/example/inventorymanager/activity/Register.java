package com.example.inventorymanager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    FirebaseAuth mauth;
    TextView loginRequest;
    EditText emailInput;
    EditText passwordInput;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize the Firebase instance
        mauth = FirebaseAuth.getInstance();


        //get the associated emailInput
        emailInput = (EditText) findViewById(R.id.reg_email);
        //get the associated password
        passwordInput = (EditText) findViewById(R.id.reg_password);

        //get the register button
        registerButton =  (Button) findViewById(R.id.register_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
//                Toast.makeText(getApplicationContext(),"Error: Blah", Toast.LENGTH_SHORT).show();

                signup(email, password);
            }
        });

        //get the associated TextView
        loginRequest = (TextView) findViewById(R.id.login_req);

        //add a click event to the text view
        loginRequest.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);

                startActivity(intent);
            }
        });
    }

    private void signup(String email, String password) {
        mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error: could not sign up", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}