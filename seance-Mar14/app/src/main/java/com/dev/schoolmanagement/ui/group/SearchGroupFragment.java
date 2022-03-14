package com.dev.schoolmanagement.ui.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dev.schoolmanagement.databinding.FragmentSearchGroupBinding;
import com.dev.schoolmanagement.viewModels.GroupViewModel;

public class SearchGroupFragment extends Fragment {
    private GroupViewModel mViewModel;

    private FragmentSearchGroupBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchGroupBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
    }
}