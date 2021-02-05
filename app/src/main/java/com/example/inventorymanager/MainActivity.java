package com.example.inventorymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavBar =(BottomNavigationView) findViewById(R.id.bottomNavBar);

        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                if(item.getItemId() == R.id.f_home){
                    fragment =  new HomeFragment();
                }
                else if(item.getItemId() == R.id.f_profile){
                    fragment = new ProfileFragment();
                }
                else if(item.getItemId() == R.id.f_logout){
                    fragment = new LogoutFragment();
                }
                else if (item.getItemId() == R.id.f_customer){
                    fragment = new CustomerFragment();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error: Could not change view", Toast.LENGTH_SHORT).show();
                }

                //set the new fragment of the view
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, fragment).commit();
                return true;
            }
        });

        //set the new fragment of the view
        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new HomeFragment()).commit();

    }
}