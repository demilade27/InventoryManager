package com.example.inventorymanager.fragment.homepages;

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
import com.example.inventorymanager.adapter.home.HomeSliderAdapter;
import com.example.inventorymanager.fragment.customerpages.NewCustomer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

     ViewPager2 pager;
     HomeSliderAdapter adapter;
     FloatingActionButton fab;
     TabLayout tabLayout;
     TabLayoutMediator tabLayoutMediator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
}
//                             }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


//        //find the views

        tabLayout =  view.findViewById(R.id.home_tab_dots);
        pager = (ViewPager2) view.findViewById(R.id.home_pager);
        fab = view.findViewById(R.id.home_fab);
//
        List<Fragment> homePages = new ArrayList<>();
        homePages.add(new HomeDashboard());
        homePages.add(new HomeActivity());
        adapter = new HomeSliderAdapter(this,homePages);
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
