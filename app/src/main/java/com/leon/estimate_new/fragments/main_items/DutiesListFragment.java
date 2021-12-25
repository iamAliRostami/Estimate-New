
package com.leon.estimate_new.fragments.main_items;

import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.CustomAdapterList;
import com.leon.estimate_new.databinding.FragmentDutiesListBinding;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.fragments.dialog.SearchFragment;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.utils.list.PrepareListData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DutiesListFragment extends Fragment {
    private final ArrayList<ExaminerDuties> examinerDuties = new ArrayList<>();
    private FragmentDutiesListBinding binding;
    private CustomAdapterList adapter;
    private Context context;

    public DutiesListFragment() {
    }

    public static DutiesListFragment newInstance() {
        return new DutiesListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDutiesListBinding.inflate(inflater, container, false);
        initialize();
        setHasOptionsMenu(false);
        return binding.getRoot();
    }

    private void initialize() {
        setHasOptionsMenu(false);
        context = requireContext();
        new PrepareListData(context, this).execute(requireActivity());
    }

    public void initializeRecyclerView(final ArrayList<ExaminerDuties> examinerDuties) {
        if (examinerDuties.isEmpty()) {
            requireActivity().runOnUiThread(() -> {
                binding.recyclerView.setVisibility(View.GONE);
                binding.textViewEmpty.setVisibility(View.VISIBLE);
            });
        } else {
            setHasOptionsMenu(true);
            this.examinerDuties.clear();
            this.examinerDuties.addAll(examinerDuties);
            adapter = new CustomAdapterList(context, this.examinerDuties);
            requireActivity().runOnUiThread(() -> {
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            ShowFragmentDialogOnce(context, "SEARCH_DIALOG", SearchFragment.newInstance(this));
        } else if (id == R.id.menu_clear) {
            adapter.filter("", "", "", "", "", "", "");
        } else if (id == R.id.menu_last) {
            adapter.filter("", getPreferenceManager().getStringData(
                    SharedReferenceKeys.TRACK_NUMBER.getValue()), "", "", "", "", "");
        }
        return super.onOptionsItemSelected(item);
    }
}