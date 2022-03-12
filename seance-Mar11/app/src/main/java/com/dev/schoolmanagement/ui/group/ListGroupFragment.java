package com.dev.schoolmanagement.ui.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.schoolmanagement.R;
import com.dev.schoolmanagement.adapters.GroupsViewAdapter;
import com.dev.schoolmanagement.databinding.FragmentListGroupBinding;
import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.models.enums.Branch;
import com.dev.schoolmanagement.models.enums.Level;
import com.dev.schoolmanagement.viewModels.GroupViewModel;


public class ListGroupFragment extends Fragment {
    public static final String TAG = "ListGroupFragment";

    private FragmentListGroupBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListGroupBinding.inflate(inflater, container, false);

        setHasOptionsMenu(true);

        navController = NavHostFragment.findNavController(ListGroupFragment.this);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GroupViewModel mViewModel = new ViewModelProvider(requireActivity()).get(GroupViewModel.class);

        mViewModel.addGroup(new Group("TDI201", Branch.TDI, Level.SpecializedTechnician));
        mViewModel.addGroup(new Group("TDI202", Branch.TDI, Level.SpecializedTechnician));

        GroupsViewAdapter mViewAdapter = new GroupsViewAdapter(mViewModel.getGroupList().getValue(), navController);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false);

        binding.groupRecyclerView.setAdapter(mViewAdapter);
        binding.groupRecyclerView.setLayoutManager(layoutManager);

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fragment_list_group_to_fragment_add_group);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_app_bar_search) {
            navController.navigate(R.id.action_fragment_list_group_to_fragment_search_group);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
