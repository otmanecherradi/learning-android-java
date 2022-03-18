package com.dev.tp1.ui.themes;

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
import com.dev.tp1.databinding.FragmentDisplayThemeBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;

import java.util.UUID;


public class DisplayThemeFragment extends Fragment {
    public static final String TAG = "DisplayThemeFragment";

    public static final String ARG_THEME_ID = "themeId";
    private Theme currentTheme;

    private FragmentDisplayThemeBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private ThemeViewModel tViewModel;

    private final int GOTO_DOCUMENTS = R.id.action_fragment_display_theme_to_fragment_list_document;
    private final int GOTO_EDIT = R.id.action_fragment_display_theme_to_fragment_edit_theme;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            UUID argThemeId = (UUID) getArguments().getSerializable(ARG_THEME_ID);
            currentTheme = DataRepository.getThemeRepository().search(argThemeId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDisplayThemeBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(DisplayThemeFragment.this);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        tViewModel = new ViewModelProvider(requireActivity()).get(ThemeViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateData();
        setClickListeners();
    }

    private void populateData() {
        mViewModel.setTitle("Theme: " + currentTheme.getName());

        binding.itemCode.setText(currentTheme.getCode().toString());
        binding.itemName.setText(currentTheme.getName());
        binding.itemDescription.setText(currentTheme.getDescription());
    }

    private void setClickListeners() {
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable(DisplayThemeFragment.ARG_THEME_ID, currentTheme.getCode());
                navController.navigate(GOTO_EDIT, bundle);
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
                        tViewModel.remove(currentTheme);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();
            }
        });

        binding.showDocumentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable(DisplayThemeFragment.ARG_THEME_ID, currentTheme.getCode());
                navController.navigate(GOTO_DOCUMENTS, bundle);
            }
        });
    }
}