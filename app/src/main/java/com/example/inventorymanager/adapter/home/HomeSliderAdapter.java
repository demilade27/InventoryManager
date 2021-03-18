package com.example.inventorymanager.adapter.home;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class HomeSliderAdapter extends FragmentStateAdapter{

     public List <Fragment> homePages;


    public HomeSliderAdapter(@NonNull Fragment fragment, List<Fragment> homePages) {
        super(fragment);
        this.homePages = homePages;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment currentFragment= homePages.get(position);

            return currentFragment;
    }

    @Override
    public int getItemCount() {
        return homePages.size();
    }
}
