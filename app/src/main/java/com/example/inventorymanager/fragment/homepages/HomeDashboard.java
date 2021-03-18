package com.example.inventorymanager.fragment.homepages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.home.HomeDashboardCardAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Card;


public class HomeDashboard extends Fragment {
    private RecyclerView recyclerView;
    private HomeDashboardCardAdapter adapter;
    private List<Card> cards;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_home_dashboard, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_dashboard_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        cards= new ArrayList<>();
        Card card = new Card("first one","name","VALUE");
        cards.add(card);

        adapter= new HomeDashboardCardAdapter(this.getActivity(),cards);
        recyclerView.setAdapter(adapter);
        //
        return view;
    }
}