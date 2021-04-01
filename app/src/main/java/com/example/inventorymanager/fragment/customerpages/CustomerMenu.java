package com.example.inventorymanager.fragment.customerpages;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.customer.CustomerMenuListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerMenu extends Fragment {
    private CardView allCustomerBtn;
    private CardView topCustomerBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_customer_menu,
                        container,
                        false);
        allCustomerBtn = view.findViewById(R.id.all_customers_btn);

        topCustomerBtn = view.findViewById(R.id.top_customers_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(getParentFragment().getView());

        allCustomerBtn.setOnClickListener(v->{
            navController.navigate(R.id.action_customerMenu_to_allCustomerPage);
        });
        topCustomerBtn.setOnClickListener(v -> {
            Fragment fragment = new AllCustomerPage();
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(this.getId(), fragment);
            transaction.commit();

        });
    }
}