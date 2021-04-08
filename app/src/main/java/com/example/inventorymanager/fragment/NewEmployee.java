package com.example.inventorymanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.Employee;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class NewEmployee extends Fragment {
    private static final String EMPLOYEE_URL = "http://192.168.64.2/inventory_manager/employee/register_employee.php";
    private TextInputLayout tilFirstName, tilLastName,tilUserName,
            tilMobileNumber, tilWorkNumber, tilEmail,tilPassword,tilLine1, tilLine2,
            tilPostcode, tilCity, tilState, tilCountry;
    private Button submitBtn, cancelBtn;
    private String firstName, lastName,userName, email, password,postcode,
            line1, line2, city, state, country;
    private int  mobileNumber,workNumber,roleId;
    private Employee employee;
    private RadioButton adminBtn;
    private RadioButton cashierBtn;
    private RadioButton accountantBtn;
    private SwitchMaterial address_switch;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_new_employee, container, false);
        findBtns(view);
        findRadios(view);
        findTextviews(view);
        address_switch= view.findViewById(R.id.employee_address_switch);
        submitBtn.setOnClickListener(this::submitOnClick);
        address_switch.setOnClickListener(this::addressSwitchOnClick);

        return view;

    }

    private void submitOnClick(View view) {
        setVariables();
        getRoleId();
        employee = new Employee(1, roleId,  mobileNumber,  workNumber,  firstName,
                lastName,  userName,  email,  postcode,  line1,
                line2,  city,  state,  country,  password);
        createEmployee();
    }

    private void cancelOnClick(View view) {
    }

    public Boolean createEmployee(){
        Map<String, String> params = this.employee.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(getContext());


        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST,EMPLOYEE_URL, new Response.Listener<String>() {
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

        return true;
    }

    public void findRadios(View view){
        adminBtn=view.findViewById(R.id.admin_radio_btn);
        cashierBtn=view.findViewById(R.id.cashier_radio_btn);
        accountantBtn=view.findViewById(R.id.accountant_radio_btn);
    }

    public void findTextviews(View view){
        tilFirstName = (TextInputLayout) view.findViewById(R.id.employee_first_name);
        tilLastName = (TextInputLayout)  view.findViewById(R.id.employee_last_name);
        tilUserName = (TextInputLayout)  view.findViewById(R.id.employee_user_name);
        tilMobileNumber = (TextInputLayout) view.findViewById(R.id.employee_mobile_number);
        tilWorkNumber = (TextInputLayout) view.findViewById(R.id.employee_work_number);
        tilEmail = (TextInputLayout)  view.findViewById(R.id.employee_email);
        tilPassword = (TextInputLayout)   view.findViewById(R.id.employee_password);
        tilLine1 = (TextInputLayout)   view.findViewById(R.id.employee_line1);
        tilLine2 = (TextInputLayout)  view.findViewById(R.id.employee_line2);
        tilPostcode = (TextInputLayout)  view.findViewById(R.id.employee_postcode);
        tilCity = (TextInputLayout) view.findViewById(R.id.employee_city);
        tilState = (TextInputLayout)  view.findViewById(R.id.employee_state);
        tilCountry = (TextInputLayout)   view.findViewById(R.id.employee_country);

    }

    private void findBtns(View view) {
        submitBtn = (Button)  view.findViewById(R.id.employee_submit_btn);
        cancelBtn = (Button)  view.findViewById(R.id.employee_cancel_btn);

    }

    private void setVariables() {
        firstName = getString(tilFirstName);
        lastName = getString(tilLastName);
        userName = getString(tilUserName);
        mobileNumber = Integer.parseInt(getString(tilMobileNumber));
        workNumber = Integer.parseInt( getString(tilWorkNumber));
        email = getString(tilEmail);
        password =getString(tilPassword);
        postcode = getString(tilPostcode);
        line1 = getString(tilLine1);
        line2 = getString(tilLine2);
        city = getString(tilCity);
        state = getString(tilState);
        country = getString(tilCountry);

    }

    private String getString(TextInputLayout TIL) {
        return TIL.getEditText().getText().toString();
    }

    public void getRoleId(){
        if(this.adminBtn.isChecked())
        {
            // is checked
            roleId= 1;
        }
        if(this.cashierBtn.isChecked())
        {
            // is checked
            roleId= 2;

        }
        if(this.accountantBtn.isChecked())
        {
            // is checked
            roleId= 3;

        }


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



}