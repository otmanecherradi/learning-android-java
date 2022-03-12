package com.dev.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.schoolmanagement.databinding.ActivityMainBinding;
import com.dev.schoolmanagement.ui.group.MainGroupActivity;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.groupsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainGroupActivity.class);
                startActivity(intent);
            }
        });

        /*

        txtName = findViewById(R.id.group_txt_name);

        spinnerBranch = findViewById(R.id.group_spinner_branch);

        radioLevel = findViewById(R.id.group_radio_level);

        addBtn = findViewById(R.id.group_add_btn);
        cancelBtn = findViewById(R.id.group_cancel_btn);

        groupListView = findViewById(R.id.group_list);

        ArrayAdapter<Branch> branchAdapter = new ArrayAdapter<>(this, R.layout.branch_element, Branch.all());
        spinnerBranch.setAdapter(branchAdapter);

        AppViewModel.getGroups().add(new Group("TDI201", Branch.TDI, Level.SpecializedTechnician));
        AppViewModel.getGroups().add(new Group("TDI202", Branch.TDI, Level.SpecializedTechnician));

        GroupListAdapter groupAdapter = GroupListAdapter.getInstance(this, AppViewModel.getGroups());

        groupListView.setAdapter(groupAdapter);

        addBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(txtName.getText())) {
                showToast("Fill in the name!");
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
        });

        cancelBtn.setOnClickListener(v -> {
            txtName.setText("");
            spinnerBranch.setSelection(-1);
            radioLevel.check(-1);

            addBtn.setText(getString(R.string.add));
        });*/
    }

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}