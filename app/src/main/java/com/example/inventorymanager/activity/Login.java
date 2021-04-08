package com.example.inventorymanager.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inventorymanager.AppConfig;
import com.example.inventorymanager.AppController;
import com.example.inventorymanager.R;
import com.example.inventorymanager.helper.SQLiteHandler;
import com.example.inventorymanager.helper.SessionManager;
import com.example.inventorymanager.model.Employee;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private SessionManager session;
    private SQLiteHandler db;
    private TextView registerRequest;
    private EditText email, password;
    private Button loginBtn;
    private String stringEmail;
    private String stringPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //link the components
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login_btn);
        registerRequest = (TextView) findViewById(R.id.reg_req);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //when the log in button is clicked
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getString();
                if (verifyPassword(stringEmail, stringEmail)) {
                    checkLogin();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //add a aclick action to it
        registerRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create a new intent
                Intent intent = new Intent(getApplicationContext(), Register.class);
                //start the activity
                startActivity(intent);
            }
        });
    }

    private void checkLogin() {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        RequestQueue queue;
        queue = Volley.newRequestQueue(getApplicationContext());


        StringRequest strReq = new StringRequest(Request.Method.POST,
              " http://192.168.64.2/inventory_manager/employee/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("info", "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // employee successfully logged in

                        // Create login session
                        session.setLogin(true);

                        // Now store the employee in SQLite
                        String uniqueId = jObj.getString(Employee.COL_UNIQUE_ID);
                        JSONObject employee = jObj.getJSONObject(Employee.TABLE_EMPLOYEE);
                        String employeeId= employee.getString(Employee.COL_EMPLOYEE_ID);
                        String roleId = employee.getString(Employee.COL_ROLE_ID);
                        String branchId = employee.getString(Employee.COL_BRANCH_ID);
                        String firstName = employee.getString(Employee.COL_FIRST_NAME);
                        String lastName = employee.getString(Employee.COL_LAST_NAME);
                        String email = employee.getString(Employee.COL_EMAIL);
                        String created_at = employee.getString(Employee.COL_CREATED_AT);


//                        addEmployee(String employeeId,String branchId,String roleId,
//                        String unique_id,String firstName,String lastName,
//                        String email, String created_at)

                        // Inserting row in employees table
                        db.addEmployee(employeeId,branchId,roleId,
                                uniqueId,firstName,lastName, email, created_at);
//                        db.deleteEmployees();

                        // Launch main activity
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put(Employee.COL_EMAIL,stringEmail);
                params.put(Employee.COL_PASSWORD, stringPassword);
                return params;
            }
        };
        // Adding request to request queue
        queue.add(strReq);

    }



    private void getString(){
        stringEmail= email.getText().toString();
        stringPassword= password.getText().toString();

    }

    private boolean verifyPassword(String email, String password) {
        if (!email.equals("") && !password.equals("")) {

            return true;
        }
        return false;
    }
}
