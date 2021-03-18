package com.example.inventorymanager.adapter.customer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CustomerSliderAdapter extends FragmentStateAdapter {

     public List <Fragment> customerPages;


    public CustomerSliderAdapter(@NonNull Fragment fragment,List<Fragment> customerPages) {
        super(fragment);
        this.customerPages = customerPages;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment currentFragment= customerPages.get(position);

            return currentFragment;
    }

    @Override
    public int getItemCount() {
        return customerPages.size();
    }
}
