package com.example.inventorymanager.fragment.customerpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.customer.CustomerMenuListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerMenu extends Fragment {

    private RecyclerView basicMenuRecycler;
    private RecyclerView smartMenuRecycler;

    private CustomerMenuListAdapter basicMenuAdapter;
    private CustomerMenuListAdapter smartMenuAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_menu,
                        container,
                        false);
        String[] basic_menu;
        String[] smart_menu;

        List<Fragment> feature_fragment= new ArrayList<>();
        basic_menu= getResources().getStringArray(R.array.customer_basic_menu_list);
        smart_menu= getResources().getStringArray(R.array.customer_menu_feature_list);


        basicMenuAdapter = new CustomerMenuListAdapter(basic_menu,feature_fragment);
        basicMenuRecycler = (RecyclerView) view.findViewById(R.id.basic_menu_recycler);
        basicMenuRecycler.setHasFixedSize(true);
        basicMenuRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        basicMenuRecycler.setAdapter(basicMenuAdapter);

        smartMenuAdapter = new CustomerMenuListAdapter(smart_menu,feature_fragment);
        smartMenuRecycler = (RecyclerView) view.findViewById(R.id.smart_menu_recycler);
        smartMenuRecycler.setHasFixedSize(true);
        smartMenuRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        smartMenuRecycler.setAdapter(smartMenuAdapter);

        return view;
    }
}