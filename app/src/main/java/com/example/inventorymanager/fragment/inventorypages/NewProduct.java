package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.R;
import com.google.android.material.button.MaterialButton;

import java.util.Map;

import com.example.inventorymanager.model.Product;



public class NewProduct extends Fragment {
    private static final String URL = "http://192.168.64.2/inventory_manager/product/create_product.php";
    private EditText tilProductName,tilProductUnit,tilProductSKU,
            tilProductSellingPrice,tilProductDescription;
    private MaterialButton cancelBtn,submitBtn;
    private String productName, productSKU,productUnit,productDescription;
    private Product product;
    private double productSellingPrice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_product, container, false);

        getAllEditText(view);
        cancelBtn = view.findViewById(R.id.product_cancel_btn);
        cancelBtn.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(),R.id.fragment_container).navigate(R.id.action_newProduct_to_fragment_inventory);


        });
        submitBtn = view.findViewById(R.id.product_submit_btn);
        submitBtn.setOnClickListener(this::submitOnclick);

        return view;
    }

    private void submitOnclick(View view){
        setVariables();
        this.product = new Product(productName,productSKU, productUnit, productSellingPrice,productDescription);
        CreateNewProduct(product);


    }

    private void CreateNewProduct(Product product) {
        Map<String, String> params = product.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(getContext());


        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST,
                URL.trim(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "response from server: " + response,
                        Toast.LENGTH_LONG).show();
                Log.i("Info", "response " + response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "response from server: " + error.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.i("Error", "Error " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                return params;
            }};
        queue.add(jsonObjectRequest);

    }

    private void getAllEditText(View view) {
        tilProductName = view.findViewById(R.id.product_name);
        tilProductUnit = view.findViewById(R.id.product_unit);
        tilProductSKU = view.findViewById(R.id.product_sku);
        tilProductSellingPrice = view.findViewById(R.id.product_selling_price);
        tilProductDescription= view.findViewById(R.id.product_description);
    }

    private String getString(EditText TIL) {
        return TIL.getText().toString();
    }

    private void setVariables() {
        productName = getString(tilProductName);
        productUnit = getString(tilProductUnit);
        productSKU = getString(tilProductSKU);
        productSellingPrice = Double.parseDouble(getString(tilProductSellingPrice));
        productDescription = getString(tilProductDescription);
    }


    }