package com.example.inventorymanager.fragment.salepages;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inventorymanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import model.Customer;
import model.Employee;
import model.Product;

import static com.android.volley.VolleyLog.wtf;

public class NewSale extends Fragment {
    TextView addItem, totalView;
    TableLayout table;
    EditText dateInput;
    ImageButton dateButton;
    Button saveButton;


    //Invoice details
    int customer_id;
    int employee_id;
    double grand_total;
    String delivery_method;
    String date_added;
    String expected_ship_date;

    String dateString;

    //For date
    private Calendar calendar;
    private int year, month, day;

    Spinner customerSpinner, employeeSpinner, productSpinner, deliveryMethodsSpinner;
    private List<Customer> allCustomers = new ArrayList<>();
    private List<Product> allProducts = new ArrayList<>();
    private List<String> allCustomersNames = new ArrayList<>();
    private List<String> allProductsNames = new ArrayList<>();
    private List<String> deliveryMethods = new ArrayList<>();
    private List<Product> newProducts = new ArrayList<>();
    private ArrayList<String> allEmployeesNames = new ArrayList<>();
    private ArrayList<Employee> allEmployees = new ArrayList<>();

    final String URL = "http://192.168.64.2/inventory_manager/customer/get_customers.php";
    final String EMPLOYEE_URL = "http://192.168.64.2/inventory_manager/employee/get_employees.php";
    final String PRODUCT_URL = "http://192.168.64.2/inventory_manager/product/get_products.php";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        For date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeSale(1,1,300,"pick up", "", "");
                Log.i("saveButton", "Save button clicked");
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
                dateInput.setText(dateString);
            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_sale_item, null);

                //get the elements in the view
                productSpinner = (Spinner) mView.findViewById(R.id.itemName);
                EditText itemDescription = (EditText) mView.findViewById(R.id.itemDescription);
                EditText itemQuantity = (EditText) mView.findViewById(R.id.itemQuantity);
                EditText itemPricePerQuantity = (EditText) mView.findViewById(R.id.itemPricePerQuantity);
                Button addItemButton = (Button) mView.findViewById(R.id.addItemButton);

                loadProducts();

                mBuilder.setView(mView);


                AlertDialog dialog = mBuilder.create();
                dialog.show();

                Spinner finalMproductSpinner = productSpinner;
                addItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //add item to database
                        String errors = "";
                        if(finalMproductSpinner.getSelectedItem().toString().isEmpty()){
                            errors += "Please enter item name \n";
                        }
                        if(itemDescription.getText().toString().isEmpty()){
                            errors += "Please enter item description \n";
                        }
                        if(itemQuantity.getText().toString().isEmpty()){
                            errors += "Please enter item quantity \n";
                        }
                        if(itemPricePerQuantity.getText().toString().isEmpty()){
                            errors += "Please enter item price per quantity \n";
                        }

                        if(errors.length() > 0){
                            Toast.makeText(getContext(), errors, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Ready to be saved in database", Toast.LENGTH_SHORT).show();
                            int itemposition =  finalMproductSpinner.getSelectedItemPosition();
                                    Product product = new Product(
                                    allProducts.get(itemposition).getProductId(),
                                    itemDescription.getText().toString(),
                                    Integer.parseInt(itemQuantity.getText().toString()),
                                    Double.parseDouble(itemPricePerQuantity.getText().toString()));
                            newProducts.add(product);
                            //close dialog modal
                            dialog.hide();

                            //reload the table
                            loadTable(newProducts);
                            totalView.setText("Total: " + getGrand_total());

                        }

                    }
                });

                //show the cancel button
                Button deleteItemButton = (Button) mView.findViewById(R.id.deleteItemButton);
                deleteItemButton.setVisibility(View.VISIBLE);
                deleteItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });


            }

        });
        loadCustomers();
        loadEmployees();
        loadDeliveryMethods();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_sales, container, false);
        //initiate variables
        table = (TableLayout) view.findViewById(R.id.main_table);
        totalView = (TextView) view.findViewById(R.id.total_view);
        addItem = (TextView) view.findViewById(R.id.addSaleItem);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        dateButton = (ImageButton) view.findViewById(R.id.dateButton);
        dateInput = (EditText) view.findViewById(R.id.editTextDate);
        customerSpinner = (Spinner) view.findViewById(R.id.customer_spinner);
        deliveryMethodsSpinner = (Spinner) view.findViewById(R.id.delivery_method_spinner);
        employeeSpinner = (Spinner) view.findViewById(R.id.employee_spinner);

        return view;
    }

    private void loadTable(List<Product> products) {

        //first remove everything in the table
        table.removeAllViews();

        TableRow tr_head = new TableRow(getContext());
        TableRow tr_head2 = new TableRow(getContext());


        tr_head.setBackgroundColor(Color.GRAY);        // part1
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        tr_head2.setBackgroundColor(Color.GRAY);        // part1
        tr_head2.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label_item_name = new TextView(getContext());
        label_item_name.setText("Name");
        label_item_name.setTextColor(Color.WHITE);          // part2
        label_item_name.setPadding(5, 5, 5, 5);
        tr_head.addView(label_item_name);// add the column to the table row here

        TextView label_item_description = new TextView(getContext());    // part3
        label_item_description.setText("Description"); // set the text for the header
        label_item_description.setTextColor(Color.WHITE); // set the color
        label_item_description.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_item_description); // add the column to the table row here

        TextView label_item_quantity = new TextView(getContext());    // part3
        label_item_quantity.setText("Quantity"); // set the text for the header
        label_item_quantity.setTextColor(Color.WHITE); // set the color
        label_item_quantity.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_item_quantity); // add the column to the table row here

        TextView label_item_price_per_quantity = new TextView(getContext());    // part3
        label_item_price_per_quantity.setText("Price/Quantity"); // set the text for the header
        label_item_price_per_quantity.setTextColor(Color.WHITE); // set the color
        label_item_price_per_quantity.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_item_price_per_quantity); // add the column to the table row here

        TextView label_item_total = new TextView(getContext());    // part3
        label_item_total.setText("Total"); // set the text for the header
        label_item_total.setTextColor(Color.WHITE); // set the color
        label_item_total.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_item_total); // add the column to the table row here

        table.setStretchAllColumns(true);

        table.addView(tr_head);


        TableRow [] trows = new TableRow[products.size()];

        for(int i = 0; i < products.size(); i++){
            trows[i] = new TableRow(getContext());
            trows[i].setId(i);
            trows[i].setBackgroundColor(Color.GRAY);        // part1
            trows[i].setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            TextView item_name = new TextView(getContext());
            item_name.setText(products.get(i).getName());
            item_name.setTextColor(Color.WHITE);          // part2
            item_name.setPadding(5, 5, 5, 5);
            trows[i].addView(item_name);// add the column to the table row here

            TextView item_description = new TextView(getContext());
            item_description.setText(products.get(i).getDescription());
            item_description.setTextColor(Color.WHITE);          // part2
            item_description.setPadding(5, 5, 5, 5);
            trows[i].addView(item_description);// add the column to the table row here

            TextView item_quantity = new TextView(getContext());
            item_quantity.setText(String.valueOf(products.get(i).getQuantity()));
            item_quantity.setTextColor(Color.WHITE);          // part2
            item_quantity.setPadding(5, 5, 5, 5);
            trows[i].addView(item_quantity); // add the column to the table row here

            TextView item_price_per_quantity = new TextView(getContext());
            item_price_per_quantity.setText(products.get(i).getSellingPrice() + "");
            item_price_per_quantity.setTextColor(Color.WHITE);          // part2
            item_price_per_quantity.setPadding(5, 5, 5, 5);
            trows[i].addView(item_price_per_quantity); // add the column to the table row here

            TextView item_total_price = new TextView(getContext());
            item_total_price.setText((products.get(i).getSellingPrice() * products.get(i).getQuantity()) + "");
            item_total_price.setTextColor(Color.WHITE);          // part2
            item_total_price.setPadding(5, 5, 5, 5);
            trows[i].addView(item_total_price); // add the column to the table row here

            //add click event to the row
            int finalI = i;

            trows[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                    View mView = getLayoutInflater().inflate(R.layout.dialog_add_sale_item, null);

                    //get the elements in the view
                    TextView modalLabel = (TextView) mView.findViewById(R.id.modal_label);
                    modalLabel.setText("Update Sale Item");

                    Spinner itemName = (Spinner) mView.findViewById(R.id.itemName);
                    itemName.setSelection(products.get(finalI).getProductId());

                    EditText itemDescription = (EditText) mView.findViewById(R.id.itemDescription);
                    itemDescription.setText(products.get(finalI).getDescription());

                    EditText itemQuantity = (EditText) mView.findViewById(R.id.itemQuantity);
                    itemQuantity.setText(String.valueOf(products.get(finalI).getQuantity()));

                    EditText itemPricePerQuantity = (EditText) mView.findViewById(R.id.itemPricePerQuantity);
                    itemPricePerQuantity.setText(products.get(finalI).getSellingPrice() + "");

                    Button addItemButton = (Button) mView.findViewById(R.id.addItemButton);
                    addItemButton.setText("Update item");

                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    addItemButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Product product = products.get(finalI);
                            product.setName(itemName.getSelectedItem().toString());
                            product.setQuantity(Integer.parseInt(itemQuantity.getText().toString()));
                            product.setDescription(itemDescription.getText().toString());
                            product.setSellingPrice(Double.parseDouble(itemPricePerQuantity.getText().toString()));

                            products.set(finalI, product);
                            loadTable(products);
                            dialog.hide();

                        }

                    });

                    //show the delete button
                    Button deleteItemButton = (Button) mView.findViewById(R.id.deleteItemButton);
                    deleteItemButton.setVisibility(View.VISIBLE);
                    deleteItemButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            products.remove(finalI);
                            loadTable(products);
                            dialog.hide();
                        }
                    });

                }

            });

            table.setStretchAllColumns(true);

            table.addView(trows[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

        }

    }

    private void loadDeliveryMethods() {
        deliveryMethods.add("Home delivery");
        deliveryMethods.add("Pick up");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, deliveryMethods);

        // attaching data adapter to spinner
        deliveryMethodsSpinner.setAdapter(dataAdapter);

        deliveryMethodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                delivery_method = parent.getItemAtPosition(position).toString();
                Log.println(Log.INFO, "Delivery method", "Delivery method is " + delivery_method);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting the string to json array object§
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray products = jsonObject.getJSONArray("Products");

                            //traversing through all the object
                            for (int i = 0; i < products.length(); i++) {
                                //getting product object from json array
                                JSONObject product = products.getJSONObject(i);

                                //JSONObject productData = product.getJSONObject("products");
                                System.out.println("Product Data : " + product.getString("product_name"));

                                Product nproduct = new Product(
                                        product.getInt(Product.COL_PRODUCT_ID),
                                        product.getString(Product.COL_PRODUCT_NAME),
                                        product.getDouble(Product.COL_SELLING_PRICE));
                                allProductsNames.add(nproduct.getName());
                                allProducts.add(nproduct);

                                // Creating adapter for the product spinner
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allProductsNames);

                                // attaching data adapter to spinner
                                productSpinner.setAdapter(dataAdapter);
                                productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                                        product_id = getProductIdFromPosition((ArrayList<Product>) allProducts, position);
//                                        Log.println(Log.INFO, "Product", "Product id is " + product_id);

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("There was an error");
                        System.out.println(error.toString());
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);

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
                            Log.i("info", "onResponse: about to loop through customers");
                            //traversing through all the object
                            for (int i = 0; i < customers.length(); i++) {
                                //getting customer object from json array
                                JSONObject customer = customers.getJSONObject(i);

                                //JSONObject productData = product.getJSONObject("products");
                                System.out.println("Customer Data : " + customer.getString("first_name"));

                                Customer ncustomer = new Customer(customer.getInt("customer_id"), customer.getString("first_name"), customer.getInt("mobile_number"), customer.getString("line_1"));
                                allCustomersNames.add(ncustomer.getFirstName());
                                //allCustomersNames.add(ncustomer.getFirstName() + " " + ncustomer.getCustomerId());
                                allCustomers.add(ncustomer);

                                // Creating adapter for the customer spinner
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allCustomersNames);

                                // attaching data adapter to spinner
                                customerSpinner.setAdapter(dataAdapter);

                                customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        customer_id = getCustomerIdFromPosition(allCustomers, position);
                                        Log.println(Log.INFO, "Customer", "Customer id is " + customer_id);

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("There was an error");
                        System.out.println(error.toString());
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

    private void loadEmployees() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, EMPLOYEE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting the string to json array object§
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray employees = jsonObject.getJSONArray("Employees");

                            //traversing through all the object
                            for (int i = 0; i < employees.length(); i++) {
                                //getting employee object from json array
                                JSONObject employee = employees.getJSONObject(i);

                                //JSONObject productData = product.getJSONObject("products");
                                System.out.println("Employee Data : " + employee.getString("first_name"));

                                Employee nemployee = new Employee(employee.getInt("employee_id"), employee.getString("first_name"), employee.getString("last_name"));
                                allEmployeesNames.add(nemployee.getFirst_name());
                                allEmployees.add(nemployee);

                                // Creating adapter for the customer spinner
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allEmployeesNames);

                                // attaching data adapter to spinner
                                employeeSpinner.setAdapter(dataAdapter);

                                employeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        employee_id = getEmployeeIdFromPosition(allEmployees, position);
                                        Log.println(Log.INFO, "Employee", "Employee id is " + employee_id);

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("There was an error");
                        System.out.println(error.toString());
                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

    private int getEmployeeIdFromPosition(ArrayList<Employee> allEmployees, int position) {
        return allEmployees.get(position).getEmployee_id();
    }

    private int getProductIdFromPosition(ArrayList<Product> allProducts, int position) {
        return allProducts.get(position).getProductId();
    }

    private int getCustomerIdFromPosition(List<Customer> allCustomers, int position) {
        return allCustomers.get(position).getCustomerId();
    }

    //calculate grand total
    private double getGrand_total(){
        double sum = 0.0;
        for(Product product : newProducts){
            sum += product.getQuantity() * product.getSellingPrice();
        }
        return sum;
    }

    //store sale to database
    private void storeSale(int customer_id, int employee_id, double grand_total, String delivery_method, String date_added, String expected_ship_date){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            String URL = "http://192.168.64.2/inventory_manager/invoice/create_invoice.php";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("customer_id", customer_id);
            jsonBody.put("employee_id", employee_id);
            jsonBody.put("grand_total", grand_total);
            jsonBody.put("delivery_method", delivery_method);
            jsonBody.put("date_added", date_added);
            jsonBody.put("expected_ship_date", expected_ship_date);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    int order_id = 0;

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        //the response will return the order id
                        order_id = jsonObject.getInt("id");

                        Log.i("VOLLEY", "last inserted id is now " + order_id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                    Log.i("VOLLEY response", "Successfully saved sale");



                    //now store the order lines
                    for(Product product : newProducts){

                        int product_id = product.getProductId();
                        double rate = product.getSellingPrice();
                        int quantity = product.getQuantity();
                        double total = rate * quantity;
                        Log.i("info", "Id"+product_id);
                        Log.i("info", "rate"+rate);
                        Log.i("info", "quantituy"+quantity);
                        Log.i("info", "total"+total);

                        storeOrderLine(order_id, product_id, rate, quantity, total);

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return super.parseNetworkResponse(response);
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    private void storeOrderLine(int order_id, int product_id, double rate, int quantity, double total){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            String URL = "http://192.168.64.2/inventory_manager/order_line/create_order_line.php";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("order_id", order_id);
            jsonBody.put("product_id", product_id);
            jsonBody.put("rate", rate);
            jsonBody.put("quantity", quantity);
            jsonBody.put("total", total);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    Log.i("VOLLEY response", "Successfully saved order line");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
        //add request
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    //For date
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        getActivity().showDialog(999);
        Toast.makeText(getContext(), "calendar",
                Toast.LENGTH_SHORT)
                .show();
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(getContext(),
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0,
                              int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateString = day + "/" + month + "/" + year;
        Toast.makeText(getContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

//            dateInput.setText(new StringBuilder().append(day).append("/")
//                    .append(month).append("/").append(year));
    }

}