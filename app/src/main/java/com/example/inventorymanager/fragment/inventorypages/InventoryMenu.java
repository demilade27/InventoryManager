package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.inventorymanager.R;


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

        topProductBtn = view.findViewById(R.id.top_product_btn);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(getParentFragment().getView());

        allProductBtn.setOnClickListener(v->{
            navController.navigate(R.id.action_inventoryMenu_to_allProducts);
        });
        topProductBtn.setOnClickListener(v -> {
            Fragment fragment = new AllProducts();
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(this.getId(), fragment);
            transaction.commit();

        });
    }
}