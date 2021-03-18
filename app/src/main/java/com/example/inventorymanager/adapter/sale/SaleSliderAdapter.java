package com.example.inventorymanager.adapter.sale;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SaleSliderAdapter extends FragmentStateAdapter{

     public List <Fragment> salepages;


    public SaleSliderAdapter(@NonNull Fragment fragment, List<Fragment> salepages) {
        super(fragment);
        this.salepages = salepages;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment currentFragment= salepages.get(position);

            return currentFragment;
    }

    @Override
    public int getItemCount() {
        return salepages.size();
    }
}
