package model;

import java.util.HashMap;
import java.util.Map;

public class Customer {



    public static final String CUSTOMER_TABLE = "customer";
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_COMPANY_NAME = "company_name";
    public static final String COL_MOBILE_NUMBER = "mobile_number";
    public static final String COL_WORK_NUMBER = "work_number";
    public static final String COL_EMAIL ="email" ;
    public static final String COL_POSTCODE = "postcode";
    public static final String COL_LINE_1 = "line_1";
    public static final String COL_LINE_2 = "line_2";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_COUNTRY = "country";
    public static final String COL_DATE_CREATED = "date_created";


    private String customerId;


    private String firstName, lastName, companyName, mobileNumber,workNumber,
            email, postcode, line1, line2, city, state, country;
     Map<String, String> postData = new HashMap<String, String>();



    public Customer(String firstName, String lastName,
                    String companyName, String mobileNumber,
                    String workNumber, String email, String postcode,
                    String line1, String line2, String city, String state,
                    String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.email = email;
        this.postcode = postcode;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
    }




    @Override
    public String toString() {
        return "Customer{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", postcode=" + postcode +
                ", email='" + email + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
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

        postData.put(COL_FIRST_NAME, getFirstName());
        postData.put(COL_LAST_NAME, getLastName());
        postData.put(COL_COMPANY_NAME, getCompanyName());
        postData.put(COL_MOBILE_NUMBER, getMobileNumber());
        postData.put(COL_WORK_NUMBER,getWorkNumber());
        postData.put(COL_EMAIL, getEmail());
        postData.put(COL_POSTCODE, getPostcode());
        postData.put(COL_LINE_1, getLine1());
        postData.put(COL_LINE_2, getLine2());
        postData.put(COL_CITY, getCity());
        postData.put(COL_STATE, getState());
        postData.put(COL_COUNTRY, getCountry());
        return this.postData;
    }
}
