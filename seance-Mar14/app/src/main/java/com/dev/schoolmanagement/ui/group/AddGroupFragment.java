package com.dev.schoolmanagement.ui.group;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.databinding.FragmentAddGroupBinding;
import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.models.enums.Branch;
import com.dev.schoolmanagement.models.enums.Level;
import com.dev.schoolmanagement.viewModels.AppViewModel;
import com.dev.schoolmanagement.viewModels.GroupViewModel;
import com.google.android.material.snackbar.Snackbar;


public class AddGroupFragment extends Fragment {
    public static final String TAG = "AddGroupFragment";

    private FragmentAddGroupBinding binding;
    private NavController navController;

    private final GroupViewModel groupViewModel = AppViewModel.getInstance().getGroupViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddGroupBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(AddGroupFragment.this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, Branch.all());
        binding.groupBranch.setAdapter(adapter);

        handleAddButtonClick();
    }

    private void handleAddButtonClick() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearProgressBar.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                View view = requireActivity().getCurrentFocus();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (TextUtils.isEmpty(binding.groupName.getText())) {
                    showSnackBar("Fill in the name!");
                    return;
                }

                if (TextUtils.isEmpty(binding.groupBranch.getText())) {
                    showSnackBar("Choose a bloody branch!");
                    return;
                }

                if (groupViewModel.searchGroupByName(binding.groupName.getText().toString()) != null) {
                    showSnackBar("Group already exists!");
                    return;
                }

                Group group = new Group();
                group.setName(binding.groupName.getText().toString());
                group.setBranch(Branch.valueOf(binding.groupBranch.getText().toString()));
                group.setLevel(binding.groupRadioLevel.getCheckedRadioButtonId() == R.id.group_radio_level_t ? Level.Technician : Level.SpecializedTechnician);

                groupViewModel.addGroup(group);

                binding.linearProgressBar.setVisibility(View.GONE);

                showSnackBarWithAction("Group added successfully!", "Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        groupViewModel.deleteGroup(group);

                        showSnackBar("Group deleted!");
                    }
                });

                navController.navigateUp();
            }
        });
    }

    public void showSnackBar(String message) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void showSnackBarWithAction(String message, String action, View.OnClickListener listener) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
                .setAction(action, listener).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}