package cat.buyaround.app.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.callback.EditUserCallback;
import cat.buyaround.app.databinding.FragmentPersonalInfoBinding;
import cat.buyaround.app.databinding.ItemPersonalInfoBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.User;
import cat.buyaround.app.network.model.SimpleResponse;
import cat.buyaround.app.viewmodel.PersonalInfoViewModel;
import dagger.android.support.DaggerFragment;

import static android.app.Activity.RESULT_OK;

public class PersonalInfoFragment extends DaggerFragment {
    private static final String TAG = "PersonalInfoFragment";

    @Inject
    protected ViewModelFactory viewModelFactory;
    @Inject
    UserManager userManager;
    private FragmentPersonalInfoBinding binding;
    private PersonalInfoViewModel personalInfoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personalInfoViewModel = new ViewModelProvider(this, viewModelFactory).get(PersonalInfoViewModel.class);

        subscribeObserver();

        binding.editPhotoBtn.setOnClickListener(v -> {
            checkForPermissions();
        });

        binding.updateBtn.setOnClickListener(v -> {
            // TODO: GET USER MODIFIED DATA
            User user = null;
            personalInfoViewModel.updateUser(user, new EditUserCallback() {
                @Override
                public void onUserUpdated() {
                    Toast.makeText(getContext(), R.string.info_updated, Toast.LENGTH_LONG).show();
                    Navigation.findNavController(v).popBackStack();
                }

                @Override
                public void onFailure(SimpleResponse.Status error) {
                    Toast.makeText(getContext(), R.string.error_updating_info, Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void checkForPermissions() {
        int permissionWrite = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionWrite != PackageManager.PERMISSION_GRANTED || permissionRead != PackageManager.PERMISSION_GRANTED) {
            askForPermission();
        } else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .setAutoZoomEnabled(true)
                    .start(requireContext(), this);
        }
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    private void subscribeObserver() {
        personalInfoViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {

            Glide.with(requireContext())
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(user.getProfilePicture())
                    .into(binding.profilePhotoIv);

            setupItemContent(binding.name, getResources().getString(R.string.personal_info_name), user.getName());
            setupItemContent(binding.surname, getResources().getString(R.string.personal_info_surname), user.getSurnames());
            setupItemContent(binding.email, getResources().getString(R.string.personal_info_email), user.getEmail());
            setupItemContent(binding.phone, getResources().getString(R.string.phone), user.getPhone());
            setupItemContent(binding.birthday, getResources().getString(R.string.personal_info_birthday), new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthday()));
            setupItemContent(binding.password, getResources().getString(R.string.personal_info_password), null);

        });
    }

    private void setupItemContent(ItemPersonalInfoBinding b, String title, String content) {
        b.itemTv.setText(title);
        if (content != null) {
            b.itemContentTv.setText(content);
        } else {
            b.itemContentTv.setText(getResources().getString(R.string.edit_password));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(),
                            result.getUri());

                    Glide.with(requireContext())
                            .asBitmap()
                            .placeholder(R.drawable.ic_thumbnail)
                            .load(bitmap)
                            .into(binding.profilePhotoIv);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                result.getError().printStackTrace();
            }
        }
    }
}
