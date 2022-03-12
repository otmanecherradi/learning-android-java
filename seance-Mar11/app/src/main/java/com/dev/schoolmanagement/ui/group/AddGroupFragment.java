package com.dev.schoolmanagement.ui.group;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dev.schoolmanagement.databinding.FragmentAddGroupBinding;
import com.dev.schoolmanagement.models.enums.Branch;
import com.google.android.material.snackbar.Snackbar;


public class AddGroupFragment extends Fragment {
    public static final String TAG = "AddGroupFragment";

    private FragmentAddGroupBinding binding;
    private AddGroupFormViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddGroupBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AddGroupFormViewModel.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, Branch.all());
        binding.groupBranch.setAdapter(adapter);

        handleNameChange();
        handleBranchChange();

        handleAddButtonClick();

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearProgressBar.setVisibility(View.VISIBLE);

                /*
                if (TextUtils.isEmpty(binding.groupName.getText())) {
                    showSnackBar("Fill in the name!");
                    return;
                }


                if (spinnerBranch.getSelectedItem() == null) {
                    showToast("Choose a bloody branch!");
                    return;
                }

                if (radioLevel.getCheckedRadioButtonId() != R.id.group_radio_level_t && radioLevel.getCheckedRadioButtonId() != R.id.group_radio_level_ts) {
                    showToast("Choose a bloody level!");
                    return;
                }

                Branch branch = Branch.valueOf(spinnerBranch.getSelectedItem().toString());
                Level level = radioLevel.getCheckedRadioButtonId() == R.id.group_radio_level_t ? Level.Technician : Level.SpecializedTechnician;

                Group group = new Group(txtName.getText().toString(), branch, level);

                if (AppViewModel.searchGroup(group.getName()) != null) {
                    showToast("Group already exist!");
                    return;
                }

                if (AppViewModel.addGroup(group))
                    showToast("Group added successfully!");
                else
                    showToast("Group not added successfully!");

                addBtn.setText(getString(R.string.add));

                groupAdapter.notifyDataSetChanged();

               */
            }
        });
    }

    private void handleNameChange() {
        TextWatcher nameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(@NonNull Editable editable) {
                mViewModel.setName(editable.toString());
            }
        };
        binding.groupName.addTextChangedListener(nameTextWatcher);
    }

    private void handleBranchChange() {
        TextWatcher branchTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(@NonNull Editable editable) {
                mViewModel.setBranch(editable.toString());
            }
        };
        binding.groupBranch.addTextChangedListener(branchTextWatcher);
    }

    private void handleAddButtonClick() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearProgressBar.setVisibility(View.VISIBLE);
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