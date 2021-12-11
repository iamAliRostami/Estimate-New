package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.os.Bundle;
import android.os.Debug;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityListBinding;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.helpers.MyApplication;

import org.jetbrains.annotations.NotNull;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
    }

    public void initializeRecyclerView() {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        if (getPreferenceManager().getStringData(SharedReferenceKeys.TRACK_NUMBER.getValue()) == null ||
                getPreferenceManager().getStringData(SharedReferenceKeys.TRACK_NUMBER.getValue()).length() < 1) {
            menu.getItem(1).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            SearchFragment searchFragment = SearchFragment.newInstance();
            searchFragment.show(fragmentTransaction, "");
        } else if (id == R.id.menu_clear) {
            customAdapter.filter("", "", "", "", "", "", "");
        } else if (id == R.id.menu_last) {
            customAdapter.filter("", getPreferenceManager().getStringData(
                    SharedReferenceKeys.TRACK_NUMBER.getValue()), "", "", "", "", "");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().maxMemory();
        Debug.getNativeHeapAllocatedSize();
    }
}