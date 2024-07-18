package com.leon.estimate_new.fragments.documents;

import static com.leon.estimate_new.enums.BundleEnum.LICENCE_TITLE;
import static com.leon.estimate_new.enums.BundleEnum.OTHER_TITLE;
import static com.leon.estimate_new.enums.BundleEnum.TITLE;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.enums.DialogType.YellowRedirect;
import static com.leon.estimate_new.fragments.dialog.ShowFragmentDialog.ShowFragmentDialogOnce;
import static com.leon.estimate_new.helpers.Constants.IMAGE_FILE_NAME;
import static com.leon.estimate_new.helpers.Constants.NECESSARY_IMAGES;
import static com.leon.estimate_new.utils.CustomFile.compressBitmap;
import static com.leon.estimate_new.utils.CustomFile.createImageFile;
import static com.leon.estimate_new.utils.CustomFile.saveTempBitmap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.activities.FinalReportActivity;
import com.leon.estimate_new.adapters.ImageViewAdapter;
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.databinding.FragmentTakePhotoBinding;
import com.leon.estimate_new.di.view_model.CustomDialogModel;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.fragments.dialog.AddDocumentFragment;
import com.leon.estimate_new.tables.DataTitle;
import com.leon.estimate_new.tables.ImageData;
import com.leon.estimate_new.tables.ImageDataThumbnail;
import com.leon.estimate_new.tables.Images;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.document.ImageThumbnail;
import com.leon.estimate_new.utils.document.ImageThumbnailList;
import com.leon.estimate_new.utils.document.UploadImages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;

public class TakePhotoFragment extends Fragment implements View.OnClickListener {
    private FragmentTakePhotoBinding binding;
    private long lastClickTime = 0;
    public Callback documentActivity;
    private int position = 0;
    private String path;
    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null &&
                                result.getData().getData() != null) {
                            try {
                                InputStream inputStream = requireContext().getContentResolver()
                                        .openInputStream(result.getData().getData());
                                Bitmap bitmap = compressBitmap(BitmapFactory.decodeStream(inputStream));
                                documentActivity.setTakenBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            documentActivity.setTakenBitmap(compressBitmap(BitmapFactory.decodeFile(path)));
                        }
                    });

    public static TakePhotoFragment newInstance() {
        return new TakePhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTakePhotoBinding.inflate(inflater, container, false);
        initialize();
//        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                if (documentActivity.isNew())
                    menuInflater.inflate(R.menu.add_document_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.add_document_menu) {
                    ShowFragmentDialogOnce(requireContext(), "ADD_DOCUMENT",
                            AddDocumentFragment.newInstance(documentActivity.getTrackNumber()));
                }
                return false;
            }
        });
    }

    private void initialize() {
        if (documentActivity.getBitmap() != null) {
            binding.imageView.setImageBitmap(documentActivity.getBitmap());
            binding.buttonUpload.setVisibility(View.VISIBLE);
        }
        if (documentActivity.getImages().isEmpty()) {
            documentActivity.setImages();
            new ImageThumbnailList(requireContext(), documentActivity.getKey(),
                    this).execute(requireActivity());
        } else binding.progressBar.setVisibility(View.GONE);
        initializeImageAdapter();
        initializeTitleSpinner();
        setOnClickListener();
    }

    private void initializeTitleSpinner() {
        if (documentActivity.getSpinnerAdapter() == null)
            documentActivity.setSpinnerAdapter(new SpinnerCustomAdapter(requireContext(),
                    documentActivity.getTitles()));
        binding.spinnerTitle.setAdapter(documentActivity.getSpinnerAdapter());
        binding.spinnerTitle.setSelection(documentActivity.getSelected());
    }

    private void setOnClickListener() {
        binding.buttonPick.setOnClickListener(this);
        binding.buttonUpload.setOnClickListener(this);
        binding.buttonAccepted.setOnClickListener(this);
    }

    public void setOldThumbnails(ImageDataThumbnail thumbnails) {
        documentActivity.setDataThumbnail(thumbnails);
        addImage();
    }

    public void addImage(ResponseBody... body) {
        if (body.length > 0) {
            Images image = new Images(documentActivity.getBillId(), documentActivity.getTrackNumber(),
                    documentActivity.getDataThumbnailUri().get(position - 1),
                    documentActivity.getDataThumbnail().get(position - 1).title_name,
                    documentActivity.getDataThumbnail().get(position - 1).title_id,
                    BitmapFactory.decodeStream(body[0].byteStream()), false);
            documentActivity.addImage(image);
            initializeImageAdapter(image);
        }
        getNextImage();
    }

    private void getNextImage() {
        if (documentActivity.getDataThumbnail().size() > position)
            new ImageThumbnail(documentActivity.getDataThumbnail().get(position).img,
                    this).execute(requireActivity());
        else binding.progressBar.setVisibility(View.GONE);
        position++;
    }

    public void saveBitmap() {
        saveTempBitmap(documentActivity.getBitmap(), requireContext(), documentActivity.getBillId(),
                documentActivity.getTrackNumber(),
                documentActivity.getDataTitle(binding.spinnerTitle.getSelectedItemPosition()).id,
                documentActivity.getDataTitle(binding.spinnerTitle.getSelectedItemPosition()).title,
                documentActivity.isNew());
    }

    public void addUploadedImage() {
        Images image = createImageObject();
        documentActivity.addImage(image);
        initializeImageAdapter(image);
    }

    private Images createImageObject() {
        return new Images(IMAGE_FILE_NAME, documentActivity.getBillId(),
                documentActivity.getTrackNumber(),
                documentActivity.getDataTitle(binding.spinnerTitle.getSelectedItemPosition()).id,
                documentActivity.getDataTitle(binding.spinnerTitle.getSelectedItemPosition()).title,
                documentActivity.getBitmap(), true);
    }

    private void initializeImageAdapter(Images... image) {
        try {
            if (image.length > 0)
                documentActivity.getImageViewAdapter().updateImagesList(image[0]);
            else {
                documentActivity.setImageViewAdapter(new ImageViewAdapter(requireContext(),
                        documentActivity.getImages()));
                binding.gridViewImage.setAdapter(documentActivity.getImageViewAdapter());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProgressBar getProgressBar() {
        return binding.progressBar;
    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_upload) {
            upload();
        } else if (id == R.id.button_pick) {
            pickImage();
        } else if (id == R.id.button_accepted) {
            accept();
        }
    }

    private void upload() {
        if (documentActivity.getBitmap() != null) {
            binding.buttonUpload.setVisibility(View.GONE);
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.icon_finder_camera));
            new UploadImages(this, documentActivity.isNew() ?
                    documentActivity.getTrackNumber() : documentActivity.getBillId(),
                    documentActivity.getDataTitle(binding.spinnerTitle.getSelectedItemPosition()).id,
                    documentActivity.isNew()).execute(requireActivity());
        }
    }

    private void pickImage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.choose_document);
        builder.setMessage(R.string.select_source);
        builder.setPositiveButton(R.string.gallery, (dialog, which) -> {
            dialog.dismiss();
            openGalleryActivityForResult();
        });
        builder.setNegativeButton(R.string.camera, (dialog, which) -> {
            dialog.dismiss();
            openCameraActivityForResult();
        });
        builder.create().show();
    }

    private void accept() {
        boolean cancel = false;
        for (String docId : NECESSARY_IMAGES) {
            cancel = true;
            final ArrayList<Images> images = documentActivity.getImages();
            for (int i = 0, imagesSize = images.size(); cancel && i < imagesSize; i++) {
                final Images image = images.get(i);
                cancel = !image.docId.equals(docId);
            }
        }//TODO
        if (!cancel)
            new CustomToast().error("مدارک الزامی الصاق نشده است.", Toast.LENGTH_LONG);
        else
            new ShowDialogue(getString(R.string.accepted_question), getString(R.string.dear_user),
                    getString(R.string.final_accepted), getString(R.string.yes));
    }

    private void openCameraActivityForResult() {
        final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(requireContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile(requireContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                path = photoFile.getPath();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(requireContext(),
                        BuildConfig.APPLICATION_ID.concat(".provider"), photoFile));
                cameraActivityResultLauncher.launch(cameraIntent);
            }
        }
    }

    private void openGalleryActivityForResult() {
        final Intent galleryIntent = new Intent("android.intent.action.PICK");
        if (galleryIntent.resolveActivity(requireContext().getPackageManager()) != null) {
            galleryIntent.setType("image/*");
            galleryActivityResultLauncher.launch(galleryIntent);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            documentActivity = (Callback) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public interface Callback {
        int getSelected();

        boolean isNew();

        Bitmap getBitmap();

        String getKey();

        String getTrackNumber();

        String getBillId();

        DataTitle getDataTitle(int position);

        ArrayList<String> getTitles();

        ArrayList<DataTitle> getDataTitle();

        ArrayList<String> getDataThumbnailUri();

        ArrayList<Images> getImages();

        ArrayList<ImageData> getDataThumbnail();

        void setDataThumbnail(ImageDataThumbnail thumbnails);

        void setTakenBitmap(Bitmap bitmap);

        void setImages();

        void addImage(Images images);

        void setSpinnerAdapter(SpinnerCustomAdapter spinnerAdapter);

        SpinnerCustomAdapter getSpinnerAdapter();

        void setImageViewAdapter(ImageViewAdapter imageViewAdapter);

        ImageViewAdapter getImageViewAdapter();
    }

    class ShowDialogue implements CustomDialogModel.Inline {
        ShowDialogue(String message, String title, String top, String positiveButtonText) {
            new CustomDialogModel(YellowRedirect, requireContext(), message,
                    title, top, positiveButtonText, this);
        }

        @Override
        public void inline() {
            if (HttpClientWrapper.call != null) {
                HttpClientWrapper.call.cancel();
                HttpClientWrapper.call = null;
            }
            final Intent intent = new Intent(requireContext(), FinalReportActivity.class);
            int titleId = 0, crookiTitleId = 0, licenceTitleId = 0;
            for (DataTitle dataTitle : documentActivity.getDataTitle()) {
                if (dataTitle.title.equals("فرم ارزیابی"))
                    titleId = dataTitle.id;
                if (dataTitle.title.equals("کروکی"))
                    crookiTitleId = dataTitle.id;
                if (dataTitle.title.equals("مجوز حفاری"))
                    licenceTitleId = dataTitle.id;
            }
            intent.putExtra(TITLE.getValue(), titleId);
            intent.putExtra(OTHER_TITLE.getValue(), crookiTitleId);
            intent.putExtra(LICENCE_TITLE.getValue(), licenceTitleId);
            intent.putExtra(TRACK_NUMBER.getValue(), documentActivity.getTrackNumber());
            startActivity(intent);
            requireActivity().finish();
        }
    }
}