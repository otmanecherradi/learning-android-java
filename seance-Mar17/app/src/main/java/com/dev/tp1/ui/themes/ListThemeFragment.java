package com.dev.tp1.ui.themes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.tp1.R;
import com.dev.tp1.data.DataRepository;
import com.dev.tp1.databinding.FragmentListThemeBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.themes.adapters.ThemeRecyclerViewAdapter;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;

import java.util.ArrayList;


public class ListThemeFragment extends Fragment {
    public static final String TAG = "AddThemeFragment";

    private FragmentListThemeBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private ThemeViewModel tViewModel;
    private ThemeRecyclerViewAdapter themeRecyclerViewAdapter;

    private final int GOTO_SEARCH_FRAGMENT = R.id.action_fragment_list_theme_to_fragment_search_theme;
    private final int GOTO_ADD_FRAGMENT = R.id.action_fragment_list_theme_to_fragment_edit_theme;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListThemeBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(ListThemeFragment.this);

        setHasOptionsMenu(true);

        binding.themesList.setLayoutManager(new LinearLayoutManager(requireContext()));
        themeRecyclerViewAdapter = new ThemeRecyclerViewAdapter(DataRepository.getThemeRepository().getThemes(), navController);
        binding.themesList.setAdapter(themeRecyclerViewAdapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        tViewModel = new ViewModelProvider(requireActivity()).get(ThemeViewModel.class);

        tViewModel.getThemes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Theme>>() {
            @Override
            public void onChanged(ArrayList<Theme> themes) {
                themeRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateData();
        setListeners();
    }

    private void populateData() {
        mViewModel.setTitle("Themes");
    }

    private void setListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(GOTO_ADD_FRAGMENT);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main_theme_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sync:
                themeRecyclerViewAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_search:
                navController.navigate(GOTO_SEARCH_FRAGMENT);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}