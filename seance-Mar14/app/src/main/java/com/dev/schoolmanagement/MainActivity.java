package com.dev.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.schoolmanagement.databinding.ActivityMainBinding;
import com.dev.schoolmanagement.ui.group.MainGroupActivity;
import com.dev.schoolmanagement.viewModels.AppViewModel;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppViewModel.getInstance(this);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        HashMap<String, String> el = new HashMap<String, String>();
        el.put("text", "Groups");

        list.add(el);

        final SimpleAdapter menuAdapter = new SimpleAdapter(this, list, R.layout.element_main_menu,
                new String[]{"text"}, new int[]{R.id.menu_element_text});

        binding.menuList.setAdapter(menuAdapter);
        binding.menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MainGroupActivity.class);
                        startActivity(intent);
                        break;
                }
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