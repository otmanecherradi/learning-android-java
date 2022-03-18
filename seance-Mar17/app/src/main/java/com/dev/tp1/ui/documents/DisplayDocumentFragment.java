package com.dev.tp1.ui.documents;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.tp1.R;
import com.dev.tp1.data.DataRepository;
import com.dev.tp1.databinding.FragmentDisplayDocumentBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Document;
import com.dev.tp1.ui.documents.viewModels.DocumentViewModel;
import com.dev.tp1.ui.themes.DisplayThemeFragment;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;

import java.util.UUID;


public class DisplayDocumentFragment extends Fragment {
    public static final String TAG = "DisplayDocumentFragment";

    public static final String ARG_DOCUMENT_ID = "documentId";
    private Document currentDocument;

    private FragmentDisplayDocumentBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private DocumentViewModel dViewModel;

    private final int GOTO_EDIT_DOCUMENT = R.id.action_fragment_list_document_to_fragment_edit_document;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID argDocumentId = (UUID) getArguments().getSerializable(ARG_DOCUMENT_ID);
            currentDocument = DataRepository.getDocumentRepository().search(argDocumentId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDisplayDocumentBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(DisplayDocumentFragment.this);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        dViewModel = new ViewModelProvider(requireActivity()).get(DocumentViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateData();
        setClickListeners();
    }

    private void populateData() {
        mViewModel.setTitle("Document");

        binding.itemCode.setText(currentDocument.getCode().toString());
        binding.itemDescription.setText(currentDocument.getDescription());
        binding.itemStatus.setText(currentDocument.getState() ? "Available" : "Not available");
    }

    private void setClickListeners() {
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable(DisplayThemeFragment.ARG_THEME_ID, currentDocument.getCode());
                navController.navigate(GOTO_EDIT_DOCUMENT, bundle);
            }
        });

        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Confirmation!");
                builder.setIcon(R.drawable.outline_warning_amber_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dViewModel.remove(currentDocument);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();
            }
        });

    }
}