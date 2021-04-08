package com.example.inventorymanager.fragment.inventorypages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Supplier;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class NewSupplier extends Fragment {
    private static final String URL = "http://192.168.64.2/inventory_manager/supplier/create_supplier.php";
    private TextInputLayout tilCompanyName,tilCompanyNumber,tilCompanyEmail,
            tilPostcode,tilLine1, tilLine2, tilCity,
            tilState, tilCountry,tilFirstName, tilLastName,
            tilMobileNumber, tilWorkNumber, tilEmail;
    private String companyName,companyNumber, companyEmail,
           postcode, line1, line2, city, state, country,
            firstName, lastName,email;
    private int mobileNumber,workNumber;    
    private Button submitBtn, cancelBtn;
    private Supplier supplier;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_supplier, container, false);
        getTextView(view);
        getBtns(view);
        submitBtn.setOnClickListener(this::submitOnClick);
        return view;
    }

    private void submitOnClick(View view) {
        setVariables();
//       todo:change BRANCH ID
        supplier = new Supplier(1,  companyName, companyNumber,
                 companyEmail,  postcode,  line1,  line2,  city,
                 state,  country,  firstName,  lastName,  mobileNumber, workNumber,  email);

        CreateNewSupplier();
    }
    private boolean CreateNewSupplier(){
        Map<String, String> params = this.supplier.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(this.getContext());


        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "response from server: " + response, Toast.LENGTH_LONG).show();
                Log.i("Info", "response " + response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "response from server: " + error.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Error", "Error " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                return params;
            }};
        queue.add(jsonObjectRequest);

        return false;
    }

    public void getTextView(View view){
        tilCompanyName =view.findViewById(R.id.supplier_company_name);
        tilCompanyNumber =view.findViewById(R.id.supplier_company_number);
        tilCompanyEmail =view.findViewById(R.id.supplier_company_email);
        tilPostcode =view.findViewById(R.id.supplier_postcode);
        tilLine1 =view.findViewById(R.id.supplier_line_1);
        tilLine2 =view.findViewById(R.id.supplier_line_2);
        tilCity =view.findViewById(R.id.supplier_city);
        tilState =view.findViewById(R.id.supplier_state);
        tilCountry =view.findViewById(R.id.supplier_country);
        tilFirstName =view.findViewById(R.id.supplier_first_name);
        tilLastName =view.findViewById(R.id.supplier_last_name);
        tilMobileNumber =view.findViewById(R.id.supplier_mobile_number);
        tilWorkNumber =view.findViewById(R.id.supplier_work_number);
        tilEmail =view.findViewById(R.id.supplier_email);

    }

    private void setVariables() {

        companyName = getString(tilCompanyName);
        companyNumber = getString(tilCompanyNumber);
        companyEmail = getString(tilCompanyEmail);
        mobileNumber = Integer.parseInt(getString(tilMobileNumber));
        workNumber = Integer.parseInt( getString(tilWorkNumber));
        email = getString(tilEmail);
        postcode = getString(tilPostcode);
        line1 = getString(tilLine1);
        line2 = getString(tilLine2);
        city = getString(tilCity);
        state = getString(tilState);
        country = getString(tilCountry);
        firstName = getString(tilFirstName);
        lastName = getString(tilLastName);
    }

    private String getString(TextInputLayout TIL) {
        return TIL.getEditText().getText().toString();
    }

    private void getBtns(View view) {
        submitBtn = (Button)  view.findViewById(R.id.supplier_submit_btn);
        cancelBtn = (Button)   view.findViewById(R.id.supplier_cancel_btn);
    }

    private void clearTextViews(){
        tilCompanyName.getEditText().setText("");
        tilCompanyNumber.getEditText().setText("");
        tilCompanyEmail.getEditText().setText("");
        tilFirstName.getEditText().setText("");
        tilLastName.getEditText().setText("");
        tilCompanyName.getEditText().setText("");
        tilMobileNumber.getEditText().setText("");
        tilWorkNumber.getEditText().setText("");
        tilEmail.getEditText().setText("");
        tilPostcode.getEditText().setText("");
        tilLine1.getEditText().setText("");
        tilLine2.getEditText().setText("");
        tilCity.getEditText().setText("");
        tilState.getEditText().setText("");
        tilCountry.getEditText().setText("");
    }

}