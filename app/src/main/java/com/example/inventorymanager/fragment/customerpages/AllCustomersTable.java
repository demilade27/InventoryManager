package com.example.inventorymanager.fragment.customerpages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.adapter.customer.AllCustomerTableAdapter;
import com.example.inventorymanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * A fragment representing a list of Items.
 */
public class AllCustomersTable extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;
    private static final String URL ="http://192.168.64.2/inventory_manager/customer/get_customers.php";
    //a list to store all the products
    List<Customer> customerList;
    //the recyclerview
    RecyclerView recyclerView;


    public AllCustomersTable() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_customer_table, container, false);
        //getting the recyclerview from xml
        recyclerView = view.findViewById(R.id.all_customer_table_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //initializing the productlist
        customerList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new AllCustomerTableAdapter(v));
//        }
//        return view;
        return view;
    }
    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object§
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray customers = jsonObject.getJSONArray("Customers");
                            //traversing through all the object
                            for (int i = 0; i < customers.length(); i++) {
                                //getting product object from json array
                                JSONObject customer = customers.getJSONObject(i);

                                //adding the product to product list
                                customerList.add(new Customer(
                                        customer.getInt(Customer.COL_CUSTOMER_ID),
                                        customer.getString(Customer.COL_FIRST_NAME),
                                        customer.getInt(Customer.COL_MOBILE_NUMBER),
                                        customer.getString(Customer.COL_LINE_1)

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            AllCustomerTableAdapter adapter = new AllCustomerTableAdapter(getContext(), customerList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}