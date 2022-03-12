package com.dev.schoolmanagement.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.ui.group.DisplayGroupFragment;

import java.util.List;

public class GroupsViewAdapter extends RecyclerView.Adapter<GroupsViewAdapter.ViewHolder> {
    List<Group> groups;
    NavController navController;

    public GroupsViewAdapter(List<Group> groups, NavController navController) {
        this.groups = groups;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Group currentGroup = groups.get(position);

        viewHolder.populateData(currentGroup);
        viewHolder.setClickListener(currentGroup, navController);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView groupItemName, groupItemLevel;
        private final TableLayout listGroupItem;

        public ViewHolder(View itemView) {
            super(itemView);

            groupItemName = itemView.findViewById(R.id.group_item_name);
            groupItemLevel = itemView.findViewById(R.id.group_item_level);
            listGroupItem = itemView.findViewById(R.id.list_group_item);
        }

        public void populateData(@NonNull Group group) {
            groupItemName.setText(group.getName());
            groupItemLevel.setText(String.valueOf(group.getLevel()));
        }

        public void setClickListener(@NonNull Group group, NavController navController) {
            listGroupItem.setOnClickListener(v -> {
                final Bundle bundle = new Bundle();
                bundle.putSerializable(DisplayGroupFragment.ARG_GROUP, group);
                navController.navigate(R.id.action_fragment_list_group_to_display_group_fragment, bundle);
            });
        }
    }

}
