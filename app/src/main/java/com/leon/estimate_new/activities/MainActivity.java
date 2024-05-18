package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.Constants.DOWNLOAD_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.DUTIES_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.EXIT_POSITION;
import static com.leon.estimate_new.helpers.Constants.HELP_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.HOME_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.OFFLINE_MAP_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.POSITION;
import static com.leon.estimate_new.helpers.Constants.REQUEST_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.UPLOAD_FRAGMENT;
import static com.leon.estimate_new.helpers.Constants.exit;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.RecyclerItemClickListener;
import com.leon.estimate_new.base_items.BaseActivity;
import com.leon.estimate_new.databinding.ActivityMainBinding;
import com.leon.estimate_new.fragments.main_items.DownloadFragment;
import com.leon.estimate_new.fragments.main_items.DutiesListFragment;
import com.leon.estimate_new.fragments.main_items.HelpFragment;
import com.leon.estimate_new.fragments.main_items.HomeFragment;
import com.leon.estimate_new.fragments.main_items.OfflineMapFragment;
import com.leon.estimate_new.fragments.main_items.SendRequestFragment;
import com.leon.estimate_new.fragments.main_items.UploadFragment;

public class MainActivity extends BaseActivity  {
    private ActivityMainBinding binding;

    @Override
    protected void initialize() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View childLayout = binding.getRoot();
        final ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        displayView(HOME_FRAGMENT/*DUTIES_FRAGMENT*/);
        setOnDrawerItemClick();
    }
    public void displayView(int position) {
        String tag = Integer.toString(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(tag) != null && fragmentManager.findFragmentByTag(tag).isVisible()) {
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(binding.containerBody.getId(), getFragment(position), tag);
        // Home fragment is not added to the stack
        if (position != HOME_FRAGMENT) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private Fragment getFragment(int position) {
        return switch (position) {
            case DUTIES_FRAGMENT -> DutiesListFragment.newInstance();
            case DOWNLOAD_FRAGMENT -> DownloadFragment.newInstance();
            case REQUEST_FRAGMENT -> SendRequestFragment.newInstance();
            case UPLOAD_FRAGMENT -> UploadFragment.newInstance();
            case HELP_FRAGMENT -> HelpFragment.newInstance();
            default -> HomeFragment.newInstance();
        };
    }

    private void setOnDrawerItemClick() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                        if (position == EXIT_POSITION) {
                            exit = true;
                            finishAffinity();
                        } else if (POSITION != position) {
                            POSITION = position;
                            displayView(POSITION);
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}