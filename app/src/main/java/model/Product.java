package model;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public static final String COL_PRODUCT_NAME = "product_name";
    public static final String COL_UNIT = "'unit'";
    public static final String COL_PRICE= "'price'";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_SKU = "sku";

    Map<String, String> postData = new HashMap<String, String>();
    private String name, unit, sku, price,description;


    @Override
    public String toString() {
        return "Product{" +
                "postData=" + postData +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", sku='" + sku + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Product(String name, String sku, String unit, String price, String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.unit = unit;
        this.price = price;
    }

    public Product(String name, String sku, String price,String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
    }
    public Product(String name, String sku,String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Map<String, String> getMap() {
        postData.put(COL_PRODUCT_NAME, getName());
        postData.put(COL_SKU,getSku());
        postData.put(COL_UNIT, getUnit());
        postData.put(COL_PRICE, getPrice());
        postData.put(COL_DESCRIPTION,getDescription());
        return this.postData;
    }
}

