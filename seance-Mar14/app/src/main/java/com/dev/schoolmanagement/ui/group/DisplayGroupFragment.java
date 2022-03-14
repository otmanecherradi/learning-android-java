package com.dev.schoolmanagement.ui.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.databinding.FragmentDisplayGroupBinding;
import com.dev.schoolmanagement.models.Group;

public class DisplayGroupFragment extends Fragment {
    public static final String TAG = "DisplayGroupFragment";

    public static final String ARG_GROUP = "group";

    private Group mGroup;
    private FragmentDisplayGroupBinding binding;

    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGroup = (Group) getArguments().getSerializable(ARG_GROUP);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDisplayGroupBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(DisplayGroupFragment.this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.groupName.setText(mGroup.getName());
        binding.groupLevel.setText(String.valueOf(mGroup.getLevel()));

        binding.updateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  navController.navigate();
            }
        });

        binding.deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(binding.getRoot().getContext())
                        .setTitle("Delete group")
                        .setMessage("U sure?")
                        .setIcon(R.drawable.outline_report_problem_24)
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                            System.out.println(mGroup);
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}