package com.example.inventorymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;

import java.util.List;

public class BaseMenuListAdapter extends RecyclerView.Adapter<BaseMenuListAdapter.ViewHolder> {

    private String[] menu;
    private List<Fragment> menuFragment;

    public BaseMenuListAdapter(String[] menu, List<Fragment> menuFragment){

        this.menu = menu;
        this.menuFragment = menuFragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  BaseMenuListAdapter.ViewHolder holder, int position) {
            holder.getView().setText(menu[position]);

    }


    @Override
    public int getItemCount() {
        return menu.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView menuItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menuItem = (TextView) itemView.findViewById(R.id.menu_item);
        }
        public TextView getView(){
            return menuItem;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
