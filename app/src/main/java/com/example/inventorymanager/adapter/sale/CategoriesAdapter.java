package com.example.inventorymanager.adapter.sale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<String> categories;
    private List<Fragment> featureFragment;
    public CategoriesAdapter(List<String> categories){

        this.categories = categories;
        this.featureFragment = featureFragment;
    }
    public CategoriesAdapter(List<String> categories, List<Fragment>featureFragment){

        this.categories = categories;
        this.featureFragment = featureFragment;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        holder.category.setText(categories.get(position));
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
        }
        @Override
        public void onClick(View v) {

        }
    }
}


