package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.inventory.InventoryDashboardCardAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class InventoryDashboard extends Fragment {
    private RecyclerView cardListRecyclerView;
    private InventoryDashboardCardAdapter cardListAdapter;
    private List<Card> cards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_inventory_dashboard,
                        container, false);

        cards= new ArrayList<>();
        Card mostRecent = new Card("Most Recent","Name","Amount");
        cards.add(mostRecent);
        Card mostSold = new Card("Most Sold Product","Name","Quantity");
        cards.add(mostSold);

        CreateDashboardCardList(view,cards);
        return view;
    }

    private void CreateDashboardCardList(ViewGroup view, List<Card> cards) {
        cardListRecyclerView = (RecyclerView) view.findViewById(R.id.inventory_dashboard_recycler);
        cardListRecyclerView.setHasFixedSize(true);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cardListAdapter= new InventoryDashboardCardAdapter(this.getActivity(),cards);
        cardListRecyclerView.setAdapter(cardListAdapter);
    }
}