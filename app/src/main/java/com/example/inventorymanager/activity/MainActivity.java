package com.example.inventorymanager.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.inventorymanager.R;
import com.example.inventorymanager.fragment.MoreFragment;
import com.example.inventorymanager.fragment.customerpages.CustomerFragment;
import com.example.inventorymanager.fragment.homepages.HomeFragment;
import com.example.inventorymanager.fragment.inventorypages.InventoryFragment;
import com.example.inventorymanager.fragment.salepages.SaleFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class  MainActivity extends AppCompatActivity {
    MaterialToolbar topAppBar;
    BottomNavigationView bottomNavBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavBar =  findViewById(R.id.bottomNavBar);
        topAppBar = findViewById(R.id.topAppBar);

        setSupportActionBar(topAppBar);
        topAppBar.setNavigationOnClickListener(v -> {


        });
        topAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.top_bar_account:
                    Toast.makeText(getApplicationContext(),"account selected",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.top_bar_more:
                    Toast.makeText(getApplicationContext(),"more selected",Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;

            }
        });
        topAppBar.setTitle("Home Page");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomNavBar.setSelectedItemId(R.id.fragment_home);
        bottomNavBar.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.fragment_home:
                    topAppBar.setTitle("Home");
                    fragment = new HomeFragment();
                    break;
                case R.id.fragment_inventory:
                    topAppBar.setTitle("Inventory");
                    fragment = new InventoryFragment();
                    break;
                case R.id.fragment_sale:
                    topAppBar.setTitle("Sale");
                    fragment = new SaleFragment();
                    break;
                case R.id.fragment_customer:
                    topAppBar.setTitle("Customer");
                    fragment = new CustomerFragment();
                    break;
                case R.id.fragment_more:
                    topAppBar.setTitle("More");
                    fragment = new MoreFragment();
                    break;
                default:
                    fragment = new HomeFragment();
                    break;
            }

            //set the new fragment of the view
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        });
    }

}
