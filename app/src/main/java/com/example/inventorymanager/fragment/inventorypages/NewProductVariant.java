package com.example.inventorymanager.fragment.inventorypages;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.inventorymanager.R;

import java.util.ArrayList;
import java.util.List;

public class NewProductVariant extends Fragment {

    private EditText etProductGroupName,etProductVariantDescription,etProductVariantUnit
            ,etProductVariantBrand,etProductVariantManufacturer;
    private String  productGroupName,productVariantDescription,productVariantUnit,
            productVariantBrand,productVariantManufacturer;
    private List<EditText> etAttribute,etOptions;
    private List<String> attribute;
    private List<String[]> options;
    TableLayout table;
    private List<EditText>etProduct_name, etSku, etCost_price,
            etSelling_price, etUpc, etEan, etIsbn;
    private List<String>product_name,sku,cost_price,
            selling_price,upc,ean,isbn;
    private String allOptions;
    private Button btngenrateTags;
    private ImageButton addAttribute2,addAttribute3,removeAttribute2,removeAttribute3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_product_variant, container, false);
        addViews(view);

        btngenrateTags.setOnClickListener(v -> {
            getViewStrings();
            for (int i = 0; i <= options.get(0).length
                    && !options.get(0).toString().isEmpty(); i++) {
                createNewRow(view);
//                for (int j = 0; j <options.get(1).length && options.get(1).toString()!=""; j++) {
//                    createNewRow(view);
//                    for (int k = 0; k <options.get(2).length && options.get(2).toString()!=""; k++) {
//                        createNewRow(view);
//                    }
//                }
            }
        });
        addAttribute2.setOnClickListener(this::addAttribute2);
        addAttribute3.setOnClickListener(this::addAttribute3);
        removeAttribute2.setOnClickListener(this::removeAttribute2);
        removeAttribute3.setOnClickListener(this::removeAttribute3);
        return view;
    }

    public void createProductVariant(){}
    
    public void addViews(View view){
        etProductGroupName = view.findViewById(R.id.product_variant_name);
        etProductVariantDescription = view.findViewById(R.id.product_variant_description);
        etProductVariantUnit = view.findViewById(R.id.product_variant_unit);
        etProductVariantBrand = view.findViewById(R.id.product_variant_brand);
        etProductVariantManufacturer=view.findViewById(R.id.product_variant_manufacturer);

        btngenrateTags=view.findViewById(R.id.product_variant_generage_tags);
        addAttribute2 = view.findViewById(R.id.product_add_attribute_2);
        addAttribute3= view.findViewById(R.id.product_add_attribute_3);
        removeAttribute2= view.findViewById(R.id.product_remove_attribute_2);
        removeAttribute3= view.findViewById(R.id.product_remove_attribute_3);

        etAttribute = new ArrayList<>();
        etOptions = new ArrayList<>();
        etAttribute.add(view.findViewById(R.id.attribute_1));
        etOptions.add(view.findViewById(R.id.product_variant_options_1));
        etAttribute.add(view.findViewById(R.id.attribute_2));
        etOptions.add(view.findViewById(R.id.product_variant_options_2));
        etAttribute.add(view.findViewById(R.id.attribute_3));
        etOptions.add(view.findViewById(R.id.product_variant_options_3));





    }

    public void getViewStrings() {
        productGroupName = etProductGroupName.getText().toString();
        productVariantDescription = etProductVariantDescription.getText().toString();
        productVariantUnit = etProductVariantUnit.getText().toString();
        productVariantBrand = etProductVariantBrand.getText().toString();
        productVariantManufacturer = etProductVariantManufacturer.getText().toString();
        attribute = new ArrayList<>();
        options = new ArrayList<>();
        attribute.add(etAttribute.get(0).getText().toString());
        options.add(etOptions.get(0).getText().toString().split(","));

//        attribute.add(etAttribute.get(1).getText().toString());
//        options.add(etOptions.get(1).getText().toString().split(","));
//        attribute.add(etAttribute.get(2).getText().toString());
//        options.add(etOptions.get(2).getText().toString().split(","));


    }

    public void createNewRow(View view){
        table = view.findViewById(R.id.product_table);
        TableRow row  =new TableRow(this.getContext());
        row.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        //create the rows
        EditText product_name = new EditText(this.getContext());
        product_name.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText sku= new EditText(this.getContext());
        sku.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        sku.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText cost_price= new EditText(this.getContext());
        cost_price.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        cost_price.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText selling_price= new EditText(this.getContext());
        selling_price.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        selling_price.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText upc= new EditText(this.getContext());
        upc.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        upc.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText ean= new EditText(this.getContext());
        ean.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        ean.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        EditText isbn= new EditText(this.getContext());
        isbn.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        isbn.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));

        //add the row to the table
        row.addView(product_name,0);
        row.addView(sku,1);
        row.addView(cost_price,2);
        row.addView(selling_price,3);
        row.addView(upc,4);
        row.addView(ean,5);
        row.addView(isbn,6);
//       System.out.println(table.getChildCount());
        //add the row
        table.addView(row,1);
    }

    public void getRowData(int rowNo){
        TableRow tableRow = (TableRow) table.getChildAt(rowNo);
        etProduct_name.add((EditText) table.getChildAt(0));
        etSku.add((EditText) table.getChildAt(1));
        etCost_price.add((EditText) table.getChildAt(2));
        etSelling_price.add((EditText) table.getChildAt(3));
        etUpc.add((EditText) table.getChildAt(4));
        etEan.add((EditText) table.getChildAt(5));
        etIsbn.add((EditText) table.getChildAt(6));
    }

    public void getRowString(){
        product_name.add(etProduct_name.get(0).getText().toString());
        sku.add(etSku.get(1).getText().toString());
        cost_price.add(etCost_price.get(2).getText().toString());
        selling_price.add(etSelling_price.get(3).getText().toString());
        upc.add(etUpc.get(4).getText().toString());
        ean.add(etEan.get(5).getText().toString());
        isbn.add(etIsbn.get(6).getText().toString());
    }

    public void addAttribute2(View view){
        etAttribute.get(1).setVisibility(View.VISIBLE);
        etOptions.get(1).setVisibility(View.VISIBLE);

        addAttribute3.setVisibility(View.VISIBLE);
        removeAttribute2.setVisibility(View.VISIBLE);
    }

    public void removeAttribute2(View view){
        etAttribute.get(1).setVisibility(View.GONE);
        etOptions.get(1).setVisibility(View.GONE);
        addAttribute3.setVisibility(View.GONE);
        removeAttribute2.setVisibility(View.GONE);

        if (etAttribute.get(2).getVisibility()==View.VISIBLE){
            removeAttribute3(view);
        }
    }

    public void addAttribute3(View view){
        etAttribute.get(2).setVisibility(View.VISIBLE);
        etOptions.get(2).setVisibility(View.VISIBLE);
        removeAttribute3.setVisibility(View.VISIBLE);



    }

    public void removeAttribute3(View view){
        etAttribute.get(2).setVisibility(View.GONE);
        etOptions.get(2).setVisibility(View.GONE);
        removeAttribute3.setVisibility(View.GONE);
    }
}