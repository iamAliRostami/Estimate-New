package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Debug;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.CustomAdapterList;
import com.leon.estimate_new.databinding.ActivityListBinding;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.list.PrepareListData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding binding;
    private final ArrayList<ExaminerDuties> examinerDuties = new ArrayList<>();
    private CustomAdapterList adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        context = this;
        new PrepareListData(context, this).execute(this);
    }

    public void initializeRecyclerView(final ArrayList<ExaminerDuties> examinerDuties) {
        if (examinerDuties.isEmpty()) {
            runOnUiThread(() -> {
                binding.recyclerView.setVisibility(View.GONE);
                binding.textViewEmpty.setVisibility(View.VISIBLE);
            });
        } else {
            this.examinerDuties.clear();
            this.examinerDuties.addAll(examinerDuties);
            adapter = new CustomAdapterList(context, this.examinerDuties);
            runOnUiThread(() -> {
                binding.textViewEmpty.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(context) {
                    @Override
                    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView parent,
                                                                 @NonNull View child,
                                                                 @NonNull Rect rect, boolean immediate) {
                        return false;
                    }
                });
            });
        }
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
//            ShowFragmentDialogOnce(context, "SEARCH_DIALOG", SearchFragment.newInstance(this));
        } else if (id == R.id.menu_clear) {
            adapter.filter("", "", "", "", "", "", "");
        } else if (id == R.id.menu_last) {
            adapter.filter("", getPreferenceManager().getStringData(
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