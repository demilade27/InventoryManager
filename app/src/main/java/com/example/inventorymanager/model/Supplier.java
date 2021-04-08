package com.example.inventorymanager.model;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
        public static final String TABLE_NAME = "supplier";
        public static final String COL_SUPPLIER_ID = "supplier_id";
        public static final String COL_BRANCH_ID = "branch_id";
        public static final String COL_COMPANY_NAME= "company_name";
        public static final String COL_COMPANY_MOBILE_NUMBER= "company_mobile_number";
        public static final String COL_COMPANY_EMAIL= "company_email";
        public static final String COL_POSTCODE = "postcode";
        public static final String COL_LINE_1 = "line_1";
        public static final String COL_LINE_2 = "line_2";
        public static final String COL_CITY = "city";
        public static final String COL_STATE = "state";
        public static final String COL_COUNTRY = "country";
        public static final String COL_FIRST_NAME = "first_name";
        public static final String COL_LAST_NAME = "last_name";
        public static final String COL_MOBILE_NUMBER = "mobile_number";
        public static final String COL_WORK_NUMBER = "work_number";
        public static final String COL_EMAIL ="email" ;
    public static final String COL_DATE_CREATED = "date_created";


        private int customerId,branchId,workNumber,mobileNumber;
        private String companyName ,companyMobileNumber,companyEmail,
                 postcode, line1, line2, city, state, country, firstName, lastName,email;
        Map<String, String> postData = new HashMap<String, String>();


    public Supplier(int branchId, String companyName, String companyMobileNumber,
                    String companyEmail, String postcode, String line1, String line2, String city,
                    String state, String country, String firstName, String lastName, int mobileNumber,
                    int workNumber, String email) {
        this.branchId = branchId;
        this.workNumber = workNumber;
        this.mobileNumber = mobileNumber;
        this.companyName = companyName;
        this.companyMobileNumber = companyMobileNumber;
        this.companyEmail = companyEmail;
        this.postcode = postcode;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Supplier(int customerId, String firstName, int mobileNumber, String line1) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.mobileNumber = mobileNumber;
        this.line1 = line1;
    }

    public Supplier(int customerId, String firstName, String lastName, String companyName, String email,int mobileNumber, int workNumber) {
        this.customerId = customerId;
        this.workNumber = workNumber;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
    }


        public int getCustomerId() {
        return customerId;
    }

        public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

        public String getFirstName() {
        return firstName;
    }

        public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        public String getLastName() {
        return lastName;
    }

        public void setLastName(String lastName) {
        this.lastName = lastName;
    }

        public String getCompanyName() {
        return companyName;
    }

        public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

        public String getEmail() {
        return email;
    }

        public void setEmail(String email) {
        this.email = email;
    }

        public Map<String, String> getMap() {
        postData.put(COL_BRANCH_ID, String.valueOf(getBranchId()));
        postData.put(COL_COMPANY_NAME, getCompanyName());
        postData.put(COL_COMPANY_MOBILE_NUMBER, getCompanyMobileNumber());
        postData.put(COL_COMPANY_EMAIL, getCompanyEmail());
        postData.put(COL_POSTCODE, getPostcode());
        postData.put(COL_LINE_1, getLine1());
        postData.put(COL_LINE_2, getLine2());
        postData.put(COL_CITY, getCity());
        postData.put(COL_STATE, getState());
        postData.put(COL_COUNTRY, getCountry());
        postData.put(COL_FIRST_NAME, getFirstName());
        postData.put(COL_LAST_NAME, getLastName());
        postData.put(COL_MOBILE_NUMBER, String.valueOf(getMobileNumber()));
        postData.put(COL_WORK_NUMBER,String.valueOf(getWorkNumber()));
        postData.put(COL_EMAIL, getEmail());
        return this.postData;
    }

    public String getCompanyMobileNumber() {
        return companyMobileNumber;
    }

    public void setCompanyMobileNumber(String companyMobileNumber) {
        this.companyMobileNumber = companyMobileNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}

