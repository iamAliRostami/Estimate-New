package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.Constants.EXIT_POSITION;
import static com.leon.estimate_new.helpers.Constants.POSITION;
import static com.leon.estimate_new.helpers.Constants.exit;
import static com.leon.estimate_new.helpers.MyApplication.getContext;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
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
import com.leon.estimate_new.fragments.DownloadFragment;
import com.leon.estimate_new.fragments.DutiesListFragment;
import com.leon.estimate_new.fragments.HelpFragment;
import com.leon.estimate_new.fragments.HomeFragment;
import com.leon.estimate_new.fragments.SendRequestFragment;
import com.leon.estimate_new.fragments.UploadFragment;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    private final int HOME_FRAGMENT = 0;
    private final int REQUEST_FRAGMENT = 1;
    private final int DOWNLOAD_FRAGMENT = 2;
    private final int DUTIES_FRAGMENT = 3;
    private final int UPLOAD_FRAGMENT = 4;
    private final int HELP_FRAGMENT = 5;

    @Override
    protected void initialize() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View childLayout = binding.getRoot();
        final ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        displayView(DUTIES_FRAGMENT);
        setOnDrawerItemClick();
    }


    private void displayView(int position) {
        final Fragment fragment;
        switch (position) {
            case DUTIES_FRAGMENT:
                fragment = DutiesListFragment.newInstance();
                break;
            case DOWNLOAD_FRAGMENT:
                fragment = DownloadFragment.newInstance();
                break;
            case REQUEST_FRAGMENT:
                fragment = SendRequestFragment.newInstance();
                break;
            case UPLOAD_FRAGMENT:
                fragment = UploadFragment.newInstance();
                break;
            case HELP_FRAGMENT:
                fragment = HelpFragment.newInstance();
                break;
            case HOME_FRAGMENT:
            default:
                fragment = HomeFragment.newInstance();
                break;
        }
        final String tag = Integer.toString(position);
        if (getFragmentManager().findFragmentByTag(tag) != null && getFragmentManager().findFragmentByTag(tag).isVisible()) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(R.id.container_body, fragment, tag);
        // Home fragment is not added to the stack
        if (position != HOME_FRAGMENT) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
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
                            POSITION = -1;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.World_Street_Map:
                return true;
            case R.id.World_Topo:
                return true;
            case R.id.Gray:
                return true;
            case R.id.Ocean_Basemap:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
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