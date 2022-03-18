package com.dev.tp1.ui.themes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.tp1.data.DataRepository;
import com.dev.tp1.databinding.FragmentEditThemeBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;


public class EditThemeFragment extends Fragment {
    public static final String TAG = "AddThemeFragment";

    public static final String ARG_THEME_ID = "themeId";
    private Theme currentTheme;

    private FragmentEditThemeBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private ThemeViewModel tViewModel;

    private boolean isEditing = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID argThemeId = (UUID) getArguments().getSerializable(ARG_THEME_ID);
            currentTheme = DataRepository.getThemeRepository().search(argThemeId);
            isEditing = true;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditThemeBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(EditThemeFragment.this);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        tViewModel = new ViewModelProvider(requireActivity()).get(ThemeViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateData();
        setListeners();
    }

    private void populateData() {
        if (isEditing) {
            mViewModel.setTitle("Edit " + currentTheme.getName());
            binding.submitBtn.setText("Edit");

            binding.nameEditText.setText(currentTheme.getName());
            binding.descriptionEditText.setText(currentTheme.getDescription());
        } else {
            mViewModel.setTitle("Adding theme");
            binding.submitBtn.setText("Add");
        }
    }

    private final View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (runValidation())
                return;

            Theme theme = new Theme();
            theme.setName(String.valueOf(binding.nameEditText.getText()));
            theme.setDescription(String.valueOf(binding.descriptionEditText.getText()));

            tViewModel.add(theme);

            showSnackBar("Theme added successfully!", "Delete", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tViewModel.remove(theme);
                }
            });

            navController.navigateUp();
        }
    };

    private final View.OnClickListener editListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (runValidation())
                return;

            Theme theme = new Theme();
            theme.setName(String.valueOf(binding.nameEditText.getText()));
            theme.setDescription(String.valueOf(binding.descriptionEditText.getText()));

            tViewModel.update(currentTheme.getCode(), theme);

            showSnackBar("Theme updated successfully successfully!", "Rollback", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tViewModel.update(currentTheme.getCode(), currentTheme);
                }
            });

            navController.navigateUp();
        }
    };

    private void setListeners() {
        if (isEditing) {
            binding.submitBtn.setOnClickListener(editListener);
        } else {
            binding.submitBtn.setOnClickListener(addListener);
        }
    }

    private boolean runValidation() {
        boolean hasError = false;
        if (TextUtils.isEmpty(binding.nameEditText.getText())) {
            showSnackBar("Name cant be empty!", null, null);
            hasError = true;
        }

        if (TextUtils.isEmpty(binding.descriptionEditText.getText())) {
            showSnackBar("Description cant be empty!", null, null);
            hasError = true;
        }

        if (DataRepository.getThemeRepository().search(String.valueOf(binding.nameEditText.getText())) != null) {
            showSnackBar("Theme already exists!", null, null);
            hasError = true;
        }

        return hasError;
    }

    private void showSnackBar(String message, String action, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG);
        if (action != null) {
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }

}