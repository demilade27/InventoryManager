package com.example.inventorymanager.model;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String COL_EMPLOYEE_ID = "employee_id";
    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_BRANCH_ID = "branch_id";
    public static final String COL_UNIQUE_ID = "unique_id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_USERNAME = "user_name";
    public static final String COL_MOBILE_NUMBER = "mobile_number";
    public static final String COL_WORK_NUMBER = "work_number";
    public static final String COL_EMAIL ="email" ;
    public static final String COL_PASSWORD ="password" ;
    public static final String COL_POSTCODE = "post_code";
    public static final String COL_LINE_1 = "line_1";
    public static final String COL_LINE_2 = "line_2";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_COUNTRY = "country";
    public static final String COL_STATUS = "status";
    public static final String COL_CREATED_AT = "created_at";
    private int employeeId;
    private int branchId;
    private int roleId;
    private int mobileNumber;
    private int workNumber;
    private String firstName, lastName,username, email, postcode,
            line1, line2, city, state, country;;
    private String password;
    Map<String, String> postData = new HashMap<String, String>();

    public Employee(int branchId, int roleId, int mobileNumber, int workNumber, String firstName,
                    String lastName, String username, String email, String postcode, String line1,
                    String line2, String city, String state, String country, String password) {
        this.branchId = branchId;
        this.roleId = roleId;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.postcode = postcode;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.password = password;
    }

    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName; }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getColEmployeeId() {
        return COL_EMPLOYEE_ID;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, String> getMap() {
        postData.put(COL_BRANCH_ID,String.valueOf(getBranchId()));
        postData.put(COL_ROLE_ID,String.valueOf(getRoleId()));
        postData.put(COL_FIRST_NAME, getFirstName());
        postData.put(COL_LAST_NAME, getLastName());
        postData.put(COL_USERNAME, getUsername());
        postData.put(COL_MOBILE_NUMBER, String.valueOf(getMobileNumber()));
        postData.put(COL_WORK_NUMBER,String.valueOf(getWorkNumber()));
        postData.put(COL_EMAIL, getEmail());
        postData.put(COL_PASSWORD, password);
        postData.put(COL_POSTCODE, getPostcode());
        postData.put(COL_LINE_1, getLine1());
        postData.put(COL_LINE_2, getLine2());
        postData.put(COL_CITY, getCity());
        postData.put(COL_STATE, getState());
        postData.put(COL_COUNTRY, getCountry());
        return this.postData;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
