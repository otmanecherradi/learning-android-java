package com.dev.tp1.ui.documents;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.tp1.R;
import com.dev.tp1.data.DataRepository;
import com.dev.tp1.databinding.FragmentEditDocumentBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Document;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.documents.viewModels.DocumentViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;


public class EditDocumentFragment extends Fragment {
    public static final String TAG = "EditDocumentFragment";

    public static final String ARG_DOCUMENT_ID = "documentId";
    private Document currentDocument;

    private FragmentEditDocumentBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private DocumentViewModel dViewModel;

    private boolean isEditing = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            UUID argDocumentId = (UUID) getArguments().getSerializable(ARG_DOCUMENT_ID);
            currentDocument = DataRepository.getDocumentRepository().search(argDocumentId);
            isEditing = true;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditDocumentBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(EditDocumentFragment.this);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        dViewModel = new ViewModelProvider(requireActivity()).get(DocumentViewModel.class);

        ArrayAdapter<Theme> themesAdapter = new ArrayAdapter<>(requireContext(), R.layout.element_theme_dropdown, DataRepository.getThemeRepository().getThemes());
        binding.themesDropdown.setAdapter(themesAdapter);

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
            mViewModel.setTitle("Editing");
            binding.submitBtn.setText("Edit");

            binding.descriptionEditText.setText(currentDocument.getDescription());
        } else {
            mViewModel.setTitle("Adding document");
            binding.submitBtn.setText("Add");
        }
    }

    private final View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (runValidation())
                return;

            Document document = new Document();
            document.setDescription(String.valueOf(binding.descriptionEditText.getText()));
            document.setState(binding.stateCheckbox.isChecked());
            // document.setThemeId(binding.themesDropdown.);

            dViewModel.add(document);

            showSnackBar("Document added successfully!", "Delete", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dViewModel.remove(document);
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

            Document document = new Document();
            document.setDescription(String.valueOf(binding.descriptionEditText.getText()));

            dViewModel.update(currentDocument.getCode(), document);

            showSnackBar("Theme updated successfully successfully!", "Rollback", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dViewModel.update(currentDocument.getCode(), currentDocument);
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
        if (TextUtils.isEmpty(binding.descriptionEditText.getText())) {
            showSnackBar("Description cant be empty!", null, null);
            hasError = true;
        }

        return hasError;
    }


    void showSnackBar(String message, String action, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG);
        if (action != null) {
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }

}