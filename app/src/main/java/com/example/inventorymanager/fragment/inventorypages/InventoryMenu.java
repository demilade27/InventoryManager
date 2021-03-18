package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.inventory.InventoryMenuListAdapter;

import java.util.ArrayList;
import java.util.List;


public class InventoryMenu extends Fragment {
    private RecyclerView basicMenuRecycler;
    private RecyclerView smartMenuRecycler;
    private InventoryMenuListAdapter basicMenuAdapter;
    private InventoryMenuListAdapter smartMenuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_inventory_menu,
                        container, false);
        List<Fragment> feature_fragment= new ArrayList<>();
        createBasicList(view, feature_fragment);
        createSmartList(view, feature_fragment);
        return view;
    }

    private void createBasicList(ViewGroup view, List<Fragment> feature_fragment) {
        String[] basic_menu;
        basic_menu= getResources().getStringArray(R.array.customer_basic_menu_list);
        basicMenuAdapter = new InventoryMenuListAdapter(basic_menu,feature_fragment);
        basicMenuRecycler = (RecyclerView) view.findViewById(R.id.inventory_basic_menu_recycler);
        basicMenuRecycler.setHasFixedSize(true);
        basicMenuRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        basicMenuRecycler.setAdapter(basicMenuAdapter);
    }

    private void createSmartList(ViewGroup view, List<Fragment> feature_fragment) {
        String[] smart_menu;
        smart_menu= getResources().getStringArray(R.array.customer_menu_feature_list);
        smartMenuAdapter = new InventoryMenuListAdapter(smart_menu,feature_fragment);
        smartMenuRecycler = (RecyclerView) view.findViewById(R.id.inventory_smart_menu_recycler);
        smartMenuRecycler.setHasFixedSize(true);
        smartMenuRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        smartMenuRecycler.setAdapter(smartMenuAdapter);
    }


}