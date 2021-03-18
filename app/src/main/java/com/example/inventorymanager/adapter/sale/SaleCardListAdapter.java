package com.example.inventorymanager.adapter.sale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

import java.util.List;

import model.CardList;

public class SaleCardListAdapter extends RecyclerView.Adapter<SaleCardListAdapter.ViewHolder> {
    private List<CardList> cardList;

    public SaleCardListAdapter(List<CardList> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleCardListAdapter.ViewHolder holder, int position) {
        CardList listItem = cardList.get(position);
        holder.label.setText(listItem.getLabel());
        holder.value.setText(listItem.getValue());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView label;
        private TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.card_list_label);
            value = itemView.findViewById(R.id.card_list_value);
        }
    }
}
