package com.example.inventorymanager.fragment.inventorypages;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.inventorymanager.R;

public class NewProductVariant extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_product_variant, container, false);

        return view;
    }
    public void createNewColumn(View view){
        TableLayout table = view.findViewById(R.id.product_table);
        TableRow row  =new TableRow(this.getContext());

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
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));


        EditText cost_price= new EditText(this.getContext());
        cost_price.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));


        EditText selling_price= new EditText(this.getContext());
        selling_price.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));


        EditText upc= new EditText(this.getContext());
        upc.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));


        EditText ean= new EditText(this.getContext());
        ean.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));


        EditText isbn= new EditText(this.getContext());
        isbn.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        product_name.setBackground(Drawable.createFromPath("@drawable/text_view_boarder"));



        //add the row to the table
        row.addView(product_name);
        row.addView(sku);
        row.addView(cost_price);
        row.addView(selling_price);
        row.addView(upc);
        row.addView(ean);
        row.addView(isbn);

        //add the row
        table.addView(row,2);
    }

    public void createAttribute(View view){

    }
}