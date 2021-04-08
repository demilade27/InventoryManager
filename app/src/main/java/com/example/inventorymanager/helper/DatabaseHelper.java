package com.example.inventorymanager.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.inventorymanager.model.Customer;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "Customers";
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_COMPANY_NAME = "company_name";
    public static final String COL_PHONE_NUMBER = "phone_number";
    public static final String COL_WORK_NUMBER = "work_number";
    public static final String COL_EMAIL ="email" ;
    public static final String COL_LINE_1 = "line_1";
    public static final String COL_LINE_2 = "line_2";
    public static final String COL_CITY = "city";
    public static final String COL_POSTCODE = "postcode";
    public static final String COL_STATE = "state";
    public static final String COL_COUNTRY = "country";
    public static final String COL_DATE_CREATED = "date_created";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "inventory.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE " + CUSTOMER_TABLE  + "( "
                + COL_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FIRST_NAME + " VARCHAR(20), "
                + COL_LAST_NAME + " VARCHAR(20), "
                + COL_COMPANY_NAME + " VARCHAR(50), "
                 + COL_PHONE_NUMBER + "  VARCHAR(20), "
                + COL_WORK_NUMBER + " VARCHAR(30), "
                + COL_EMAIL + " VARCHAR(100), "
                + COL_LINE_1 + " TEXT,"
                + COL_LINE_2 + " TEXT,"
                + COL_CITY + " VARCHAR(20),"
                + COL_POSTCODE + " VARCHAR(7),"
                + COL_STATE + " VARCHAR(20),"
                + COL_COUNTRY + " VARCHAR(20),"
                + COL_DATE_CREATED + " VARCHAR(10)" + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FIRST_NAME,customer.getFirstName());
        cv.put(COL_LAST_NAME,customer.getLastName());
        cv.put(COL_COMPANY_NAME,customer.getCompanyName());
        cv.put(COL_PHONE_NUMBER,customer.getMobileNumber());
        cv.put(COL_WORK_NUMBER,customer.getWorkNumber());
        cv.put(COL_EMAIL,customer.getEmail());
        cv.put(COL_LINE_1,customer.getLine1());
        cv.put(COL_LINE_2,customer.getLine2());
        cv.put(COL_CITY,customer.getCity());
        cv.put(COL_POSTCODE,customer.getPostcode());
        cv.put(COL_STATE,customer.getState());
        cv.put(COL_COUNTRY,customer.getCountry());



       long result = db.insert(CUSTOMER_TABLE,null,cv);
       if (result == -1){
           return false;
       }
       else{
           return true;
       }

    }


}
