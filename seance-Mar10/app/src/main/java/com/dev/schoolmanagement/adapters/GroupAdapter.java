package com.dev.schoolmanagement.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.dev.schoolmanagement.EditActivity;
import com.dev.schoolmanagement.MainActivity;
import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.models.enums.Branch;
import com.dev.schoolmanagement.models.enums.Level;
import com.dev.schoolmanagement.viewModels.AppViewModel;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {
    static GroupAdapter groupAdapter;

    MainActivity mainActivity;
    ArrayList<Group> groups;

    public GroupAdapter(MainActivity mainActivity, ArrayList<Group> groups) {
        this.mainActivity = mainActivity;
        this.groups = groups;
    }

    public static GroupAdapter getInstance(MainActivity mainActivity, ArrayList<Group> groups) {
        if (groupAdapter == null)
            groupAdapter = new GroupAdapter(mainActivity, groups);
        return groupAdapter;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mainActivity).inflate(R.layout.group_element, parent, false);

        Group currentGroup = (Group) getItem(position);

        TextView textView = convertView.findViewById(R.id.group_detail);
        textView.setText(String.format("%s/%s", currentGroup.getBranch().toString(), currentGroup.getName()));


        Button editButton = convertView.findViewById(R.id.group_edit_btn);
        Button deleteButton = convertView.findViewById(R.id.group_delete_btn);

        Button addButton = mainActivity.findViewById(R.id.group_add_btn);

        editButton.setOnClickListener(v -> {
            addButton.setText("Edit");

            Intent intent = new Intent(mainActivity, EditActivity.class);
            intent.putExtra("group", currentGroup);

            mainActivity.startActivity(intent);

            notifyDataSetChanged();
        });

        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(mainActivity)
                    .setTitle("Delete group")
                    .setMessage("U sure?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                        AppViewModel.deleteGroup(currentGroup);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton(android.R.string.no, null).show();

        });

        return convertView;
    }
}
