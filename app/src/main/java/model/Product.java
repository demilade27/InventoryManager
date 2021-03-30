package model;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public static final String COL_PRODUCT_ID = "product_id";
    public static final String COL_PRODUCT_NAME = "product_name";
    public static final String COL_UNIT = "'unit'";
    public static final String COL_SELLING_PRICE = "'selling_price'";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_SKU = "sku";

    Map<String, String> postData = new HashMap<String, String>();
    private int productId;
    private String sellingPrice;
    private String name, unit, sku,description;


    @Override
    public String toString() {
        return "Product{" +
                "postData=" + postData +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", sku='" + sku + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Product(String name, String sku, String unit, String sellingPrice, String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.unit = unit;
        this.sellingPrice = sellingPrice;
    }

    public Product(String name, String sku, String sellingPrice, String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.sellingPrice = sellingPrice;
    }
    public Product(String name, String sku,String description){
        this.name = name;
        this.description = description;
        this.sku = sku;
    }
    public Product(int productId, String name, String sellingPrice){
        this.productId= productId;
        this.name = name;
        this.sellingPrice = sellingPrice;
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

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Map<String, String> getMap() {
        postData.put(COL_PRODUCT_NAME, getName());
        postData.put(COL_SKU,getSku());
        postData.put(COL_UNIT, getUnit());
        postData.put(COL_SELLING_PRICE, getSellingPrice());
        postData.put(COL_DESCRIPTION,getDescription());
        return this.postData;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

