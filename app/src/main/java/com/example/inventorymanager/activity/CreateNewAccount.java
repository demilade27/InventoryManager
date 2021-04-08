package com.example.inventorymanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import com.example.inventorymanager.model.Branch;
import com.example.inventorymanager.model.Employee;

public class CreateNewAccount extends AppCompatActivity {
    private static final String BRANCH_URL = "http://192.168.64.2/inventory_manager/branch/create_branch.php";
    private static final String EMPLOYEE_URL = "http://192.168.64.2/inventory_manager/employee/register_employee.php";

    private TextInputLayout tilBranchName,tilCompanyName,tilFirstName, tilLastName,tilUserName,
            tilMobileNumber, tilWorkNumber, tilEmail,tilPassword,tilLine1, tilLine2,
            tilPostcode, tilCity, tilState, tilCountry;
    private Button submitBtn, cancelBtn;
    private String branchName,companyName ,firstName, lastName,userName, email, password,postcode,
            line1, line2, city, state, country;
    private int  mobileNumber,workNumber;
    private Employee employee;
    private Branch branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        findTextviews();
        findBtns();
        submitBtn.setOnClickListener(this::submitOnClick);
        cancelBtn.setOnClickListener(this::cancelOnClick);

    }
    private void submitOnClick(View view) {
        setVariables();
        branch = new Branch(branchName,companyName);
        createNewBranch();
    }

    private void cancelOnClick(View view) {
    }

    public Boolean createNewBranch(){
        Map<String, String> params = this.branch.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(this);


        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST,BRANCH_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int branchId = 0;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //the response will return the order id
                    branchId = jsonObject.getInt("id");

                    Log.i("VOLLEY", "last inserted id is now " + branchId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("Info", "response " + response);
                employee = new Employee(branchId, 1,  mobileNumber,  workNumber,  firstName,
                        lastName,  userName,  email,  postcode,  line1,
                        line2,  city,  state,  country,  password);
                createAdmin();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "response from server: " + error.getMessage(), Toast.LENGTH_LONG).show();
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

    public Boolean createAdmin(){
        Map<String, String> params = this.employee.getMap();
        RequestQueue queue;
        queue = Volley.newRequestQueue(this);


        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST,EMPLOYEE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "response from server: " + response, Toast.LENGTH_LONG).show();
                Log.i("Info", "response " + response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "response from server: " + error.getMessage(), Toast.LENGTH_LONG).show();
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

    public void findTextviews(){
        tilCompanyName = (TextInputLayout) findViewById(R.id.cna_company_name);
        tilBranchName= (TextInputLayout) findViewById(R.id.cna_branch_name);
        tilFirstName = (TextInputLayout) findViewById(R.id.cna_first_name);
        tilLastName = (TextInputLayout)  findViewById(R.id.cna_last_name);
        tilUserName = (TextInputLayout)  findViewById(R.id.cna_user_name);
        tilMobileNumber = (TextInputLayout) findViewById(R.id.cna_mobile_number);
        tilWorkNumber = (TextInputLayout) findViewById(R.id.cna_work_number);
        tilEmail = (TextInputLayout)  findViewById(R.id.cna_email);
        tilPassword = (TextInputLayout)   findViewById(R.id.cna_password);
        tilLine1 = (TextInputLayout)   findViewById(R.id.cna_line1);
        tilLine2 = (TextInputLayout)  findViewById(R.id.cna_line2);
        tilPostcode = (TextInputLayout)  findViewById(R.id.cna_postcode);
        tilCity = (TextInputLayout) findViewById(R.id.cna_city);
        tilState = (TextInputLayout)  findViewById(R.id.cna_state);
        tilCountry = (TextInputLayout)   findViewById(R.id.cna_country);

    }

    private void setVariables() {
        branchName  = getString(tilBranchName);
        companyName = getString(tilCompanyName);
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

    private void findBtns() {
            submitBtn = (Button)  findViewById(R.id.cna_submit_btn);
            cancelBtn = (Button)  findViewById(R.id.cna_cancel_btn);

}

    private String getString(TextInputLayout TIL) {
        return TIL.getEditText().getText().toString();
    }

}