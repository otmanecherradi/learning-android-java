package com.dev.tp1.ui.documents;

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
import com.dev.tp1.databinding.FragmentListDocumentBinding;
import com.dev.tp1.modelViews.MainViewModel;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.documents.adapters.DocumentRecyclerViewAdapter;
import com.dev.tp1.ui.themes.viewModels.ThemeViewModel;

import java.util.ArrayList;


public class ListDocumentFragment extends Fragment {
    public static final String TAG = "ListDocumentFragment";

    private FragmentListDocumentBinding binding;
    private NavController navController;
    private MainViewModel mViewModel;
    private ThemeViewModel tViewModel;

    private DocumentRecyclerViewAdapter documentRecyclerViewAdapter;

    private final int GOTO_SEARCH = R.id.action_fragment_list_document_to_fragment_search_document;
    private final int GOTO_EDIT = R.id.action_fragment_list_document_to_fragment_edit_document;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListDocumentBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(ListDocumentFragment.this);

        setHasOptionsMenu(true);

        binding.documentList.setLayoutManager(new LinearLayoutManager(requireContext()));
        documentRecyclerViewAdapter = new DocumentRecyclerViewAdapter(DataRepository.getDocumentRepository().getDocuments(), navController);
        binding.documentList.setAdapter(documentRecyclerViewAdapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        tViewModel = new ViewModelProvider(requireActivity()).get(ThemeViewModel.class);

        tViewModel.getThemes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Theme>>() {
            @Override
            public void onChanged(ArrayList<Theme> themes) {
                documentRecyclerViewAdapter.notifyDataSetChanged();
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
                navController.navigate(GOTO_EDIT);
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
                documentRecyclerViewAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_search:
                navController.navigate(GOTO_SEARCH);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}