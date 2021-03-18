package com.example.inventorymanager.fragment.salepages;

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
import com.example.inventorymanager.adapter.sale.SaleSliderAdapter;
import com.example.inventorymanager.fragment.customerpages.NewCustomer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class SaleFragment extends Fragment {
    ViewPager2 pager;
    SaleSliderAdapter adapter;
    FloatingActionButton fab;
    TabLayout tabLayout;
    TabLayoutMediator tabLayoutMediator;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //find views
        tabLayout =  view.findViewById(R.id.sale_tab_dots);
        pager = view.findViewById(R.id.sale_pager);
        fab = view.findViewById(R.id.sale_fab);

        //initiate variables
        List<Fragment> salePages = new ArrayList<>();
        salePages.add(new SaleShop());
        salePages.add(new SaleDashboard());
        salePages.add(new SaleMenu());

        adapter = new SaleSliderAdapter(this,salePages);
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