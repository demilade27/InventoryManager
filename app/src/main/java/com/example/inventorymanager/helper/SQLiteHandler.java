package com.example.inventorymanager.helper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.inventorymanager.model.Employee;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE= "CREATE TABLE " + Employee.TABLE_EMPLOYEE+ "("
//                + "uid" + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Employee.COL_EMPLOYEE_ID + " INTEGER NOT NULL,"
                + Employee.COL_BRANCH_ID + " INTEGER NOT NULL,"
                + Employee.COL_ROLE_ID + " INTEGER NOT NULL,"
                + Employee.COL_UNIQUE_ID + " TEXT,"
                + Employee.COL_FIRST_NAME + " TEXT,"
                + Employee.COL_LAST_NAME + " TEXT,"
                + Employee.COL_EMAIL + " TEXT NOT NULL UNIQUE,"
                + Employee.COL_STATUS + "TEXT,"
                + Employee.COL_CREATED_AT + " TEXT" + ")";

        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Employee.TABLE_EMPLOYEE);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing employee details in database
     * */
    public void addEmployee(String employeeId,String branchId,String roleId,
                            String unique_id,String firstName,String lastName,
                            String email, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.COL_EMPLOYEE_ID, employeeId); // employee id
        values.put(Employee.COL_BRANCH_ID, branchId); // branch id
        values.put(Employee.COL_ROLE_ID, roleId); // role id
        values.put(Employee.COL_UNIQUE_ID, unique_id); // unique id
        values.put(Employee.COL_FIRST_NAME, firstName); // first Name
        values.put(Employee.COL_LAST_NAME, lastName); // last Name
        values.put(Employee.COL_EMAIL, email); // Email
        values.put(Employee.COL_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(Employee.TABLE_EMPLOYEE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Employee inserted into sqlite: " + id);
    }

    /**
     * Getting employee data from database
     * */
    public HashMap<String, String> getEmployeeDetails() {
        HashMap<String, String> employee = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + Employee.TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            employee.put("name", cursor.getString(1));
            employee.put("email", cursor.getString(2));
            employee.put("uid", cursor.getString(3));
            employee.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return employee
        Log.d(TAG, "Fetching employee from Sqlite: " + employee.toString());

        return employee;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteEmployees() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(Employee.TABLE_EMPLOYEE, null, null);
        db.close();

        Log.d(TAG, "Deleted all employee info from sqlite");
    }

}