package com.example.inventorymanager.fragment.customerpages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DatabaseHelper;
import model.Customer;

public class NewCustomer extends Fragment {
    private static final String URL = "http://192.168.64.2/inventory_manager/customer/create_customer.php";
    private TextInputLayout tilFirstName, tilLastName, tilCompanyName,
            tilMobileNumber, tilWorkNumber, tilEmail,
            tilLine1, tilLine2, tilPostcode,
            tilCity, tilState, tilCountry;
    private Button submitBtn, cancelBtn;
    private SwitchMaterial address_switch;
    private String firstName, lastName, companyName,
            email, postcode, line1,
            line2, city, state, country;
    private int  mobileNumber,workNumber;

    private DatabaseHelper dbh;
    private ProgressBar progressBar;
    private Customer customer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_customer,
                container, false);
        // find views
        findTILs(view);
        findBtns(view);
        findSwitchs(view);
        //Set on click listeners
        submitBtn.setOnClickListener(this::submitOnClick);
        cancelBtn.setOnClickListener(this::cancelOnClick);
        address_switch.setOnClickListener(this::addressSwitchOnClick);

        return view;
    }

    private void findSwitchs(View view) {
        address_switch = (SwitchMaterial) view.findViewById(R.id.address_switch);
    }

    private void cancelOnClick(View view) {
        Navigation.findNavController(getActivity(),R.id.fragment_container).navigate(R.id.action_newCustomer_to_fragment_customer);

    }

    private void findBtns(View view) {
        submitBtn = (Button)  view.findViewById(R.id.customer_submit_btn);
        cancelBtn = (Button)   view.findViewById(R.id.customer_cancel_btn);
    }

    private void findTILs(View view) {
        tilFirstName = (TextInputLayout) view.findViewById(R.id.customer_first_name);
        tilLastName = (TextInputLayout)  view.findViewById(R.id.customer_last_name);
        tilCompanyName = (TextInputLayout) view.findViewById(R.id.customer_company_name);
        tilMobileNumber = (TextInputLayout)   view.findViewById(R.id.customer_mobile_number);
        tilWorkNumber = (TextInputLayout)  view.findViewById(R.id.customer_work_number);
        tilEmail = (TextInputLayout)   view.findViewById(R.id.customer_email);
        tilLine1 = (TextInputLayout)   view.findViewById(R.id.customer_line1);
        tilLine2 = (TextInputLayout)  view.findViewById(R.id.customer_line2);
        tilPostcode = (TextInputLayout)  view.findViewById(R.id.customer_postcode);
        tilCity = (TextInputLayout)  view.findViewById(R.id.customer_city);
        tilState = (TextInputLayout)   view.findViewById(R.id.customer_state);
        tilCountry = (TextInputLayout)  view. findViewById(R.id.customer_country);
    }

    private void submitOnClick(View v) {



        setVariables();
        customer = new Customer(
                firstName, lastName, companyName,
                mobileNumber, workNumber,
                email,postcode, line1, line2, city,
                state, country);
        CreateNewCustomer(customer);

    }

    private void setVariables() {
        firstName = getString(tilFirstName);
        lastName = getString(tilLastName);
        companyName = getString(tilCompanyName);
        mobileNumber = Integer.parseInt(getString(tilMobileNumber));
        workNumber = Integer.parseInt( getString(tilWorkNumber));
        email = getString(tilEmail);
        postcode = getString(tilPostcode);
        line1 = getString(tilLine1);
        line2 = getString(tilLine2);
        city = getString(tilCity);
        state = getString(tilState);
        country = getString(tilCountry);
    }
    @NotNull
    private String getString(TextInputLayout TIL) {
        return TIL.getEditText().getText().toString();
    }

    private boolean CreateNewCustomer(Customer customer){
        Map<String, String> params = customer.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(getContext());


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

    private void clearTextViews(){
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

    private void addressSwitchOnClick(View v){
        if (address_switch.isChecked()){
            tilLine1.setVisibility(View.VISIBLE);
            tilLine2.setVisibility(View.VISIBLE);
            tilPostcode.setVisibility(View.VISIBLE);
            tilCity.setVisibility(View.VISIBLE);
            tilState.setVisibility(View.VISIBLE);
            tilCountry.setVisibility(View.VISIBLE);
            address_switch.setText("Don't Have An Address?");

        }
        else {
            tilLine1.setVisibility(View.GONE);
            tilLine2.setVisibility(View.GONE);
            tilPostcode.setVisibility(View.GONE);
            tilCity.setVisibility(View.GONE);
            tilState.setVisibility(View.GONE);
            tilCountry.setVisibility(View.GONE);
            address_switch.setText("Have an Address?");
        }

    }

    private boolean validateName(){
        if (firstName.isEmpty()){
            tilFirstName.setError("Field can't be empty");
            return false;

        }else {
            tilFirstName.setError(null);
            return true;

        }

    }

    private boolean validateNumber(int number){
        if (firstName.isEmpty()){
            tilFirstName.setError("Field can't be empty");
            return false;

        }else {
            tilFirstName.setError(null);
            return true;

        }

    }

    private boolean emailValidityCheck(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean phoneValidityCheck (String email) {
        String regex =
                "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}




