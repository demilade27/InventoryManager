package com.example.inventorymanager.adapter.sale;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

import java.util.List;

import model.TopProduct;

public class TopProductsAdapter extends RecyclerView.Adapter<TopProductsAdapter.ViewHolder> {
    private List<TopProduct> topProducts;

    public TopProductsAdapter(List<TopProduct> topProducts) {
        this.topProducts = topProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_product_row_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopProduct topProduct = topProducts.get(position);
        //set variales
        holder.name.setText(topProduct.getName());
        holder.description.setText(topProduct.getDescription());
    }

    @Override
    public int getItemCount() {
        return topProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.top_product_image);
            name = itemView.findViewById(R.id.top_product_name);
            description = itemView.findViewById(R.id.top_product_desc);

        }
    }
}
