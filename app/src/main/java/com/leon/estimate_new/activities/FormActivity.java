package com.leon.estimate_new.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityFormBinding;
import com.leon.estimate_new.enums.BundleEnum;
import com.leon.estimate_new.fragments.PersonalFragment;
import com.leon.estimate_new.tables.ExaminerDuties;

public class FormActivity extends AppCompatActivity implements PersonalFragment.Callback {
    private ActivityFormBinding binding;
    private ExaminerDuties examinerDuties;
    private final int PERSONAL_FRAGMENT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        if (getIntent().getExtras() != null) {
            final String json = getIntent().getExtras().getString(BundleEnum.EXAMINER_DUTY.getValue());
            examinerDuties = new Gson().fromJson(json, ExaminerDuties.class);
        }
        displayView(PERSONAL_FRAGMENT);
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else super.onBackPressed();

    }

    @Override
    public void setOnNextClickListener() {

    }

    public void setTitle(String title, boolean showMenu) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showMenu);
        // Set logo
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    private void displayView(int position) {
        final Fragment fragment;
        switch (position) {
//            case PERSONAL_FRAGMENT:
//                fragment = HelpFragment.newInstance();
//                break;
            case PERSONAL_FRAGMENT:
            default:
                fragment = PersonalFragment.newInstance();
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
        if (position != PERSONAL_FRAGMENT) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void setExaminerDuty(final ExaminerDuties examinerDuties) {
        this.examinerDuties = examinerDuties;
    }

    @Override
    public ExaminerDuties getExaminerDuty() {
        return examinerDuties;
    }
}