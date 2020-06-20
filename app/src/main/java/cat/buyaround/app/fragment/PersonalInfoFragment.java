package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.databinding.FragmentPersonalInfoBinding;
import cat.buyaround.app.databinding.ItemPersonalInfoBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.User;
import cat.buyaround.app.viewmodel.PersonalInfoViewModel;
import dagger.android.support.DaggerFragment;

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

        //binding.registerName.addTextChangedListener(watcher);
        //binding.registerSurname.addTextChangedListener(watcher);
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
            setupItemContent(binding.birthday, getResources().getString(R.string.personal_info_birthday), new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthday()));
            setupItemContent(binding.password, getResources().getString(R.string.personal_info_password), null);

        });
    }

    private void setupItemContent(ItemPersonalInfoBinding b, String title, String content) {
        b.itemTv.setText(title);
        if (content != null)
            b.itemContentTv.setText(content);
        else
            b.itemContentTv.setText(getResources().getString(R.string.edit_password));

    }


    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            /*
            boolean userModified = false;
            if (!stringsAreEqual(binding.registerName.getText().toString(), originalUser.getName())) {
            userModified = true;
            } else if (!stringsAreEqual(binding.registerSurname.getText().toString(), originalUser.getSurnames())) {
            userModified = true;
            }

            binding.updateButton.setEnabled(userModified);
             */
        }
    };

    private static boolean stringsAreEqual(String a, String b) {
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty())) return true;
        if (a == null || b == null) return false;

        return a.equals(b);
    }
}
