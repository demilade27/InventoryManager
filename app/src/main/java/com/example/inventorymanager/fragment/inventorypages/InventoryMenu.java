package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.TopProduct;


public class InventoryMenu extends Fragment {
    private CardView allProductBtn;
    private CardView topProductBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_inventory_menu,
                        container, false);
        allProductBtn = view.findViewById(R.id.all_product_btn);
        allProductBtn.setOnClickListener(v -> {
            Fragment fragment = new AllProducts();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(this.getId(), AllProducts.class,null);
            transaction.commit();

        });
        topProductBtn = view.findViewById(R.id.top_product_btn);
        topProductBtn.setOnClickListener(v -> {
            Fragment fragment = new AllProducts();
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(this.getId(), fragment);
            transaction.commit();

        });



        return view;
    }



}