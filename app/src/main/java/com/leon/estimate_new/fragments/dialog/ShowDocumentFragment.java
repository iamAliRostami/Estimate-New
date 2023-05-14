package com.leon.estimate_new.fragments.dialog;

import static com.leon.estimate_new.enums.BundleEnum.BILL_ID;
import static com.leon.estimate_new.enums.BundleEnum.IS_NEIGHBOUR;
import static com.leon.estimate_new.enums.BundleEnum.NEW_ENSHEAB;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.utils.CustomFile.loadImage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.leon.estimate_new.adapters.ImageViewAdapter;
import com.leon.estimate_new.databinding.FragmentShowDocumentBinding;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.tables.ImageData;
import com.leon.estimate_new.tables.ImageDataThumbnail;
import com.leon.estimate_new.tables.ImageDataTitle;
import com.leon.estimate_new.tables.Images;
import com.leon.estimate_new.utils.estimating.ImageThumbnail;
import com.leon.estimate_new.utils.estimating.ImageThumbnailList;
import com.leon.estimate_new.utils.estimating.ImageTitles;
import com.leon.estimate_new.utils.estimating.LoginDocument;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public class ShowDocumentFragment extends DialogFragment {
    private final ArrayList<ImageData> dataThumbnail = new ArrayList<>();
    private final ArrayList<String> dataThumbnailUri = new ArrayList<>();
    private ImageViewAdapter imageViewAdapter;
    private boolean isNew, isNeighbour;
    private String billId, trackNumber;
    private int position = 0;
    private FragmentShowDocumentBinding binding;

    public ShowDocumentFragment() {
    }

    public static ShowDocumentFragment newInstance(String billId, boolean isNew, boolean isNeighbour
            , String... trackNumber) {
        final ShowDocumentFragment fragment = new ShowDocumentFragment();
        final Bundle args = new Bundle();
        if (trackNumber.length > 0)
            args.putString(TRACK_NUMBER.getValue(), trackNumber[0]);
        args.putString(BILL_ID.getValue(), billId);
        args.putBoolean(NEW_ENSHEAB.getValue(), isNew);
        args.putBoolean(IS_NEIGHBOUR.getValue(), isNeighbour);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isNew = getArguments().getBoolean(NEW_ENSHEAB.getValue());
            isNeighbour = getArguments().getBoolean(IS_NEIGHBOUR.getValue());
            trackNumber = getArguments().getString(TRACK_NUMBER.getValue());
            billId = getArguments().getString(BILL_ID.getValue());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowDocumentBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        new LoginDocument(requireContext(), this).execute(requireActivity());
    }

    public void successLogin() {
        new ImageTitles(requireContext(), this).execute(requireActivity());
    }

    public void setTitles(ImageDataTitle body) {
        if (!isNeighbour) {
            final ArrayList<Images> images = new ArrayList<>(loadImage(trackNumber, billId,
                    body.data, requireContext()));
            imageViewAdapter = new ImageViewAdapter(requireContext(), images);
            binding.gridViewImage.setAdapter(imageViewAdapter);
        }
        new ImageThumbnailList(requireContext(), isNew ? trackNumber : billId, this).execute(requireActivity());
    }

    public ProgressBar getProgressBar() {
        return binding.progressBar;
    }

    public void setThumbnails(final ImageDataThumbnail thumbnails) {
        dataThumbnail.addAll(thumbnails.data);
        for (ImageData data : dataThumbnail) {
            dataThumbnailUri.add(data.img);
        }
        setImage();
    }

    public void setImage(ResponseBody... body) {//TODO
        try {
            if (body.length > 0) {
                imageViewAdapter.updateImagesList(new Images(billId, trackNumber,
                        dataThumbnailUri.get(position - 1), dataThumbnail.get(position - 1).title_name,
                        dataThumbnail.get(position - 1).title_id, BitmapFactory.decodeStream(body[0].byteStream()),
                        false));
            }
            if (dataThumbnail.size() > position)
                new ImageThumbnail(dataThumbnail.get(position).img, this).execute(requireActivity());
            else binding.progressBar.setVisibility(View.GONE);
            position++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (HttpClientWrapper.call != null) {
            HttpClientWrapper.call.cancel();
            HttpClientWrapper.call = null;
        }
    }

    @Override
    public void onResume() {
        if (getDialog() != null) {
            final WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes(params);
        }
        super.onResume();
    }
}