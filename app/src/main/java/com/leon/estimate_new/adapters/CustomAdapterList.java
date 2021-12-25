package com.leon.estimate_new.adapters;

import static com.leon.estimate_new.enums.BundleEnum.EXAMINER_DUTY;
import static com.leon.estimate_new.enums.SharedReferenceKeys.TRACK_NUMBER;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.FormActivity;
import com.leon.estimate_new.adapters.holders.ViewHolderList;
import com.leon.estimate_new.enums.BundleEnum;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.tables.ExaminerDuties;
import com.leon.estimate_new.tables.RequestDictionary;
import com.leon.estimate_new.utils.CustomToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class CustomAdapterList extends RecyclerView.Adapter<ViewHolderList> {
    private Context context;
    private int size = 0;
    private final ArrayList<ExaminerDuties> examinerDuties = new ArrayList<>();
    private final ArrayList<ExaminerDuties> examinerDutiesTemp = new ArrayList<>();

    public CustomAdapterList(Context context, ArrayList<ExaminerDuties> examinerDuties) {
        examinerDuties.sort(Comparator.comparing(ExaminerDuties::isPeymayesh).thenComparing(ExaminerDuties::getExaminationDay));
        this.context = context;
        this.examinerDuties.addAll(examinerDuties);
        this.examinerDutiesTemp.addAll(examinerDuties);
    }

    @SuppressLint("InflateParams")
    @NotNull
    public ViewHolderList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view;
        if (size % 2 == 0)
            view = layoutInflater.inflate(R.layout.item_address_1, viewGroup, false);
        else
            view = layoutInflater.inflate(R.layout.item_address_2, viewGroup, false);
        final ViewHolderList holder = new ViewHolderList(view);

        holder.itemView.setOnClickListener(view1 -> {
            if (examinerDutiesTemp.get(i).isPeymayesh()) {
                new CustomToast().success(context.getString(R.string.is_peymayesh), Toast.LENGTH_LONG);
            } else {
                final Intent intent = new Intent(context, FormActivity.class);
                final String json = new Gson().toJson(examinerDutiesTemp.get(i));
                intent.putExtra(EXAMINER_DUTY.getValue(), json);

                getPreferenceManager().putData(TRACK_NUMBER.getValue(), examinerDutiesTemp.get(i).trackNumber);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderList viewHolder, int i) {
        ExaminerDuties examinerDuties = getItem(i);

        viewHolder.textViewName.setText(examinerDuties.nameAndFamily != null && examinerDuties.nameAndFamily.trim().length() > 0 ?
                examinerDuties.nameAndFamily.trim() : "-");
        if (examinerDuties.isPeymayesh()) {
            viewHolder.textViewPeymayesh.setText("پیمایش شده");
            viewHolder.textViewPeymayesh.setBackground(ContextCompat.getDrawable(context, R.drawable.border_green_2));

        } else {
            viewHolder.textViewPeymayesh.setText("پیمایش نشده");
            viewHolder.textViewPeymayesh.setBackground(ContextCompat.getDrawable(context, R.drawable.border_red_2));
        }
        viewHolder.textViewExaminationDay.setText(examinerDuties.getExaminationDay());
        viewHolder.textViewServiceGroup.setText(examinerDuties.serviceGroup);
        viewHolder.textViewAddress.setText(examinerDuties.address != null && !examinerDuties.address.isEmpty() ?
                examinerDuties.address.trim() : "-");
        viewHolder.textViewRadif.setText(examinerDuties.radif != null && examinerDuties.radif.trim().length() > 0 ?
                examinerDuties.radif : "-");
        viewHolder.textViewTrackNumber.setText(examinerDuties.trackNumber);
        viewHolder.textViewNotificationMobile.setText(examinerDuties.notificationMobile);
        viewHolder.textViewMoshtarakMobile.setText(examinerDuties.moshtarakMobile);
        if (examinerDuties.billId != null)
            viewHolder.textViewBillId.setText(examinerDuties.billId);
        else {
            viewHolder.textViewBillId.setText(examinerDuties.neighbourBillId);
            viewHolder.textViewBillIdTitle.setText(context.getString(R.string.neighbour_bill_id));
        }
        viewHolder.textViewName.setGravity(1);
        viewHolder.textViewPeymayesh.setGravity(1);
        viewHolder.textViewExaminationDay.setGravity(1);
        viewHolder.textViewServiceGroup.setGravity(1);
        viewHolder.textViewAddress.setGravity(1);
        viewHolder.textViewRadif.setGravity(1);
        viewHolder.textViewTrackNumber.setGravity(1);
        viewHolder.textViewNotificationMobile.setGravity(1);
        viewHolder.textViewMoshtarakMobile.setGravity(1);
        viewHolder.textViewBillId.setGravity(1);

        size++;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String billId, String trackingNumber, String name, String family,
                       String nationId, String mobile, String date) {
        billId = billId.toLowerCase(Locale.getDefault());
        trackingNumber = trackingNumber.toLowerCase(Locale.getDefault());
        name = name.toLowerCase(Locale.getDefault());
        family = family.toLowerCase(Locale.getDefault());
        nationId = nationId.toLowerCase(Locale.getDefault());
        mobile = mobile.toLowerCase(Locale.getDefault());
        examinerDutiesTemp.clear();
        ArrayList<ExaminerDuties> list = new ArrayList<>(examinerDuties);
        if (billId.length() == 0)
            examinerDutiesTemp.addAll(list);
        else {
            for (ExaminerDuties examinerDuty : list) {
                if (((examinerDuty.billId != null
                        && examinerDuty.billId.toLowerCase(Locale.getDefault()).contains(billId))
                        || (examinerDuty.neighbourBillId != null
                        && examinerDuty.neighbourBillId.toLowerCase(Locale.getDefault()).contains(billId)))
                ) {
                    examinerDutiesTemp.remove(examinerDuty);
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (trackingNumber.length() > 0) {
            examinerDutiesTemp.clear();
            for (ExaminerDuties examinerDuty : list) {
                if (examinerDuty.trackNumber != null &&
                        examinerDuty.trackNumber.toLowerCase(Locale.getDefault()).contains(trackingNumber)) {
                    examinerDutiesTemp.remove(examinerDuty);
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (name.length() > 0) {
            examinerDutiesTemp.clear();
            for (ExaminerDuties examinerDuty : list) {
                if (examinerDuty.nameAndFamily != null &&
                        examinerDuty.nameAndFamily.toLowerCase(Locale.getDefault()).contains(name)) {
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (family.length() > 0) {
            examinerDutiesTemp.clear();
            for (ExaminerDuties examinerDuty : list) {
                if (examinerDuty.nameAndFamily != null &&
                        examinerDuty.nameAndFamily.toLowerCase(Locale.getDefault()).contains(family)) {
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (nationId.length() > 0) {
            examinerDutiesTemp.clear();
            for (ExaminerDuties examinerDuty : list) {
                if (examinerDuty.nationalId != null &&
                        examinerDuty.nationalId.toLowerCase(Locale.getDefault()).contains(nationId)) {
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (mobile.length() > 0) {
            examinerDutiesTemp.clear();
            for (ExaminerDuties examinerDuty : list) {
                if ((examinerDuty.mobile != null &&
                        examinerDuty.mobile.toLowerCase(Locale.getDefault()).contains(mobile))
                        || (examinerDuty.moshtarakMobile != null &&
                        examinerDuty.moshtarakMobile.toLowerCase(Locale.getDefault()).contains(mobile))
                        || (examinerDuty.notificationMobile != null &&
                        examinerDuty.notificationMobile.toLowerCase(Locale.getDefault()).contains(mobile))) {
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
            list.clear();
            list.addAll(examinerDutiesTemp);
        }
        if (date.length() > 0) {
            examinerDutiesTemp.clear();
            date = date.substring(2);
            for (ExaminerDuties examinerDuty : list) {
                if ((examinerDuty.getExaminationDay() != null &&
                        examinerDuty.getExaminationDay().toLowerCase(Locale.getDefault()).contains(date))) {
                    examinerDutiesTemp.add(examinerDuty);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return examinerDutiesTemp.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ExaminerDuties getItem(int position) {
        return examinerDutiesTemp.get(position);
    }

}

