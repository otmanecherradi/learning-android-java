package com.dev.tp1.ui.documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.tp1.databinding.FragmentSearchDocumentBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.documents.adapters.DocumentRecyclerViewAdapter;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;

import java.util.ArrayList;


public class SearchDocumentFragment extends Fragment {
    public static final String TAG = "DisplayThemeFragment";

    public static final String ARG_THEME_ID = "themeId";
    private Theme currentTheme;

    private FragmentSearchDocumentBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private ThemeViewModel tViewModel;

    private DocumentRecyclerViewAdapter documentRecyclerViewAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchDocumentBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(SearchDocumentFragment.this);

        setHasOptionsMenu(true);

//        binding.themesList.setLayoutManager(new LinearLayoutManager(requireContext()));
        // documentRecyclerViewAdapter = new DocumentRecyclerViewAdapter(DataRepository.getThemeRepository().getThemes(), navController);
//        binding.themesList.setAdapter(themeRecyclerViewAdapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        tViewModel = new ViewModelProvider(requireActivity()).get(ThemeViewModel.class);

        tViewModel.getThemes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Theme>>() {
            @Override
            public void onChanged(ArrayList<Theme> themes) {
//                themeRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.setTitle("Search document");
    }
}