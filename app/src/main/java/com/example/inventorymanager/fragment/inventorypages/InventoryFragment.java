package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.inventory.InventorySliderAdapter;
import com.example.inventorymanager.fragment.NewEmployee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class InventoryFragment extends Fragment {


    private ViewPager2 pager;
    private InventorySliderAdapter adapter;
    FloatingActionButton fab;
    TabLayout tabLayout;
    TabLayoutMediator tabLayoutMediator;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        tabLayout =  view.findViewById(R.id.inventory_tab_dots);
        pager =  view.findViewById(R.id.inventory_pager);
        fab = view.findViewById(R.id.inventory_fab);

        //initiate variables
        List<Fragment> inventoryPages = new ArrayList<>();
        inventoryPages.add(new InventoryDashboardNavHost());
        inventoryPages.add(new InventoryMenuNavHost());
        inventoryPages.add(new NewEmployee());
        adapter = new InventorySliderAdapter(this,inventoryPages);
        tabLayoutMediator = new TabLayoutMediator(tabLayout, pager, (tab, position) -> tab.setIcon(R.drawable.tab_indicator_default));
        fab.setOnLongClickListener(v -> {
            Navigation.findNavController(getActivity(),R.id.fragment_container).
                    navigate(R.id.action_fragment_inventory_to_newProductVariant);
            return true;
        });
        fab.setOnClickListener(v ->{
            Navigation.findNavController(getActivity(),R.id.fragment_container).navigate(R.id.action_fragment_inventory_to_newProduct);

        });
        pager.setAdapter(adapter);
        tabLayoutMediator.attach();
        return view;
    }
}

