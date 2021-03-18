package com.example.inventorymanager.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.CardListAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.CardList;

public class HomeDashboardCardAdapter extends RecyclerView.Adapter<HomeDashboardCardAdapter.ViewHolder>{
    private Context activity;
    private List<Card>cards;

    public HomeDashboardCardAdapter(Activity activity, List<Card> cards) {
        this.activity = activity;
        this.cards=cards;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_list,
                    parent,false);

        return new ViewHolder(view,activity.getApplicationContext(),cards);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDashboardCardAdapter.ViewHolder holder, int position) {
        Card card= cards.get(position);
        holder.title.setText(card.getTitle());
        holder.label.setText(card.getLabel());
        holder.value.setText(card.getValue());
        List<CardList> cardList = new ArrayList<>();

        //add your card item here
        cardList.add(new CardList("demilade","50"));
        cardList.add(new CardList("demilade","50"));
        cardList.add(new CardList("demilade","50"));
        cardList.add(new CardList("demilade","50"));

        CardListAdapter listAdapter = new CardListAdapter(cardList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        holder.list.setLayoutManager(linearLayoutManager);
        holder.list.setAdapter(listAdapter);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView label;
        private TextView value;
        private RecyclerView list;

        public ViewHolder(@NonNull View itemView,Context context,List<Card>cards) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.card_list_header_title);
            label = (TextView) itemView.findViewById(R.id.card_list_header_label);
            value = (TextView) itemView.findViewById(R.id.card_list_header_value);
            list = (RecyclerView)itemView.findViewById(R.id.card_list_recycler);

        }

        @Override
        public void onClick(View v) {

        }
    }

}