package com.example.inventorymanager.fragment.salepages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.adapter.sale.CategoriesAdapter;
import com.example.inventorymanager.adapter.sale.TopProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.inventorymanager.model.TopProduct;


public class SaleShop extends Fragment {
    private RecyclerView topProductRecyclerView;
    private RecyclerView categoryRecyclerView;
    private CategoriesAdapter Categoryadapter;

    private TopProductsAdapter topProductadapter;
    private List<TopProduct> topProducts;
    private List<String> categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater
                .inflate(R.layout.fragment_sale_shop,
                        container, false);
        createTopProducts(view);
        createCategories(view);

        return view;
    }

    private void createTopProducts(ViewGroup view) {
        topProductRecyclerView = (RecyclerView) view.findViewById(R.id.top_product_recycler);
        topProductRecyclerView.setHasFixedSize(true);
        topProductRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false));

        topProducts = new ArrayList<>();
        topProducts.add(new TopProduct("product one","this is the first product"));
        topProducts.add(new TopProduct("product two","this is the two product"));
        topProducts.add(new TopProduct("product three","this is the three product"));
        topProducts.add(new TopProduct("product four","this is the four product"));
        topProducts.add(new TopProduct("product five","this is the five product"));
        topProductadapter= new TopProductsAdapter(topProducts);
        topProductRecyclerView.setAdapter(topProductadapter);
    }
    private void createCategories(ViewGroup view) {
        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        categories =new ArrayList<>();
        categories.add("category 1");
        categories.add("category 2");
        categories.add("category 3");
        categories.add("category 4");
        categories.add("category 5");

        Categoryadapter= new CategoriesAdapter(categories);
        categoryRecyclerView.setAdapter(Categoryadapter);
    }
}

