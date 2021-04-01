package com.example.inventorymanager.adapter.inventory;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dummy.DummyContent.DummyItem;

import java.util.List;

import model.Product;


public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ViewHolder> {

    private List<Product> products;

    public AllProductsAdapter(List<Product> products){
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        Product product=products.get(position);
        holder.id.setText(Integer.toString(product.getProductId()));
        holder.name.setText(product.getName());
        holder.sellingPrice.setText(Double.toString(product.getSellingPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView image;
        TextView name;
        TextView id;
        TextView sellingPrice;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name_txt);
            id = view.findViewById(R.id.product_id_txt);
            sellingPrice = view.findViewById(R.id.product_price_txt);


        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }
}