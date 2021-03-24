package com.example.inventorymanager.fragment.customerpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.customer.CustomerSliderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class CustomerFragment extends Fragment {
    ViewPager2 pager;
    CustomerSliderAdapter adapter;
    FloatingActionButton fab;
    TabLayout tabLayout;
    TabLayoutMediator tabLayoutMediator;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //find the views
        tabLayout =  view.findViewById(R.id.customer_tab_dots);
        pager =  view.findViewById(R.id.customer_pager);
        fab = view.findViewById(R.id.customer_fab);

        //initiate variables
        List<Fragment> customerPages = new ArrayList<>();
        customerPages.add(new CustomerDashboard());
        customerPages.add(new CustomerMenu());
        customerPages.add(new AllCustomersTable());
        adapter = new CustomerSliderAdapter(this,customerPages);
        tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, (tab, position) -> tab.setIcon(R.drawable.tab_indicator_default));
        fab.setOnClickListener(v ->{
            Fragment fragment = new NewCustomer();
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(this.getId(), fragment);
            transaction.commit();
        });

        pager.setAdapter(adapter);
        tabLayoutMediator.attach();
    }
}