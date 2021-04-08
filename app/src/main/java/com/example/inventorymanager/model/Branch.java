package com.example.inventorymanager.model;

import java.util.HashMap;
import java.util.Map;

public class Branch {
    private final static String COL_BRANCH_NAME = "branch_name";
    private final static String COL_COMPANY_NAME = "company_name";
    int branch_id;
    String branch_name;
    String company_name;
    private Map<String, String> postData =new HashMap<String, String>();

    public Branch(int branch_id, String branch_name, String company_name) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.company_name = company_name;
    }

    public Branch(String branch_name, String company_name) {
        this.branch_name = branch_name;
        this.company_name = company_name;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public Map<String, String> getMap() {
         this.postData.put(COL_BRANCH_NAME,getBranch_name());
         this.postData.put(COL_COMPANY_NAME,getCompany_name());
         return this.postData;
    }

}
