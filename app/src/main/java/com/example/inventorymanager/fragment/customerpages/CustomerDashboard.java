package com.example.inventorymanager.fragment.customerpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.customer.CustomerDashboardCardAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Card;


public class CustomerDashboard extends Fragment {

    private RecyclerView cardListRecyclerView;
    private CustomerDashboardCardAdapter cardListAdapter;
    private List<Card> cards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view;
        view = (ViewGroup) inflater.inflate(R.layout.fragment_customer_dashboard, container, false);
        cards= new ArrayList<>();

        Card card = new Card("first one","name","VALUE");
        cards.add(card);
        CreateDashboardCardList(view,cards);

        return view;
    }
    private void CreateDashboardCardList(ViewGroup view, List<Card> cards) {
        cardListRecyclerView = (RecyclerView) view.findViewById(R.id.customer_dashboard_recycler);
        cardListRecyclerView.setHasFixedSize(true);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cardListAdapter = new CustomerDashboardCardAdapter(this.getActivity(),cards);
        cardListRecyclerView.setAdapter(cardListAdapter);
    }

}