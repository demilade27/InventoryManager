package com.example.inventorymanager.adapter.inventory;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class InventorySliderAdapter extends FragmentStateAdapter{

     public List <Fragment> inventoryPages;


    public InventorySliderAdapter(@NonNull Fragment fragment, List<Fragment> inventoryPages) {
        super(fragment);
        this.inventoryPages = inventoryPages;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment currentFragment= inventoryPages.get(position);

            return currentFragment;
    }

    @Override
    public int getItemCount() {
        return inventoryPages.size();
    }
}
