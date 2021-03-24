package com.example.inventorymanager.adapter.customer;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inventorymanager.R;

import java.util.List;
import model.Customer;


public class AllCustomerTableAdapter extends RecyclerView.Adapter<AllCustomerTableAdapter.ViewHolder> {

    private Context context;
    private List<Customer> customersList;
    public AllCustomerTableAdapter(Context context,
                                   List<Customer> customersList) {
        this.context = context;

        this.customersList = customersList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_table_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Customer customer = customersList.get(position);
        holder.customer_id.setText(String.valueOf(customer.getCustomerId()));
        holder.first_name.setText((customer).getFirstName());
        holder.mobile_number.setText(String.valueOf(customer.getMobileNumber()));
        holder.line_1.setText(customer.getLine1());

    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customer_id;
        TextView first_name;
        TextView mobile_number;
        TextView line_1;

        public ViewHolder(View view) {
            super(view);
            customer_id=(TextView) view.findViewById(R.id.customer_id);
            first_name =(TextView)view.findViewById(R.id.customer_first_name);
            mobile_number =(TextView)view.findViewById(R.id.customer_mobile_number);
            line_1 =(TextView)view.findViewById(R.id.customer_line_1);

        }

    }
}