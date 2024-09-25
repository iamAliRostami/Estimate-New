package com.leon.estimate_new.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.leon.estimate_new.fragments.forms.BaseInfoFragment;
import com.leon.estimate_new.fragments.forms.EditMapFragment;
import com.leon.estimate_new.fragments.forms.MapDescriptionFragment;
import com.leon.estimate_new.fragments.forms.PersonalFragment;
import com.leon.estimate_new.fragments.forms.ServicesFragment;
import com.leon.estimate_new.fragments.forms.TechnicalInfoFragment;

import java.util.HashMap;

public class ViewPagerStateAdapter extends FragmentStateAdapter {

    private final HashMap<Integer, Fragment> fragmentCache = new HashMap<>();

    public ViewPagerStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

//        fragmentCache.put(0, PersonalFragment.newInstance());
//        fragmentCache.put(1, ServicesFragment.newInstance());
//        fragmentCache.put(2, BaseInfoFragment.newInstance());
//        fragmentCache.put(3, TechnicalInfoFragment.newInstance());
//        fragmentCache.put(4, MapDescriptionFragment.newInstance());
//        fragmentCache.put(5, EditMapFragment.newInstance());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        Fragment cachedFragment = fragmentCache.get(position);
//        if (cachedFragment != null) {
//            return cachedFragment;
//        }
        Fragment newFragment = switch (position) {
            case 1 -> ServicesFragment.newInstance();
            case 2 -> BaseInfoFragment.newInstance();
            case 3 -> TechnicalInfoFragment.newInstance();
            case 4 -> MapDescriptionFragment.newInstance();
            case 5 -> EditMapFragment.newInstance();
            default -> PersonalFragment.newInstance();
        };

//        fragmentCache.put(position, newFragment);
        return newFragment;
    }

    @Override
    public int getItemCount() {
        return  6/*fragmentCache.size()*/;
    }
}
