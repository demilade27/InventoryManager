package com.example.inventorymanager.adapter.customer;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inventorymanager.R;

import java.util.List;

import com.example.inventorymanager.model.Customer;


public class AllCustomerPageAdapter extends RecyclerView.Adapter<AllCustomerPageAdapter.ViewHolder> {

    private List<Customer> customerList;

    public AllCustomerPageAdapter( List<Customer> customerList) {
        this.customerList= customerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_page_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Customer  customer = customerList.get(position);
        holder.customerName.setText(customer.getFirstName()+" "+customer.getLastName());
        holder.customerCompanyName.setText(customer.getCompanyName());
        holder.email.setText(customer.getEmail());
        holder.mobileNumber.setText(Integer.toString(customer.getMobileNumber()));


    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerName;
        TextView customerCompanyName;
        TextView email;
        TextView mobileNumber;

        public ViewHolder(View view) {
            super(view);
            customerName =  view.findViewById(R.id.customer_name_text);
            customerCompanyName =view.findViewById(R.id.customer_company_name_text);
            email = view.findViewById(R.id.customer_email_text);
            mobileNumber =view.findViewById(R.id.customer_phone_number_text);

        }

    }
}