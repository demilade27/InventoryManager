package com.example.inventorymanager.fragment.customerpages;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.inventorymanager.adapter.customer.AllCustomerPageAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.inventory.AllProductsAdapter;
import com.example.inventorymanager.dummy.DummyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Product;

/**
 * A fragment representing a list of Items.
 */
public class AllCustomerPage extends Fragment {
    private List<Customer> customerList;
    private RecyclerView recyclerView;
    private static final String URL ="http://192.168.64.2/inventory_manager/customer/get_customers.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_customer_page, container, false);

        recyclerView= view.findViewById(R.id.all_customer_list_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        customerList = new ArrayList<>();
        loadCustomers();
        return view;
    }
    private void loadCustomers() {

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
                                        customer.getString(Customer.COL_LAST_NAME),
                                        customer.getString(Customer.COL_COMPANY_NAME),
                                        customer.getString(Customer.COL_EMAIL),
                                        customer.getInt(Customer.COL_MOBILE_NUMBER),
                                        customer.getInt(Customer.COL_WORK_NUMBER)
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            AllCustomerPageAdapter adapter = new AllCustomerPageAdapter(customerList);
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