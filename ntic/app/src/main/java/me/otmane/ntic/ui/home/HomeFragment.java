package me.otmane.ntic.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.R;
import me.otmane.ntic.databinding.ElementDoubleRvBinding;
import me.otmane.ntic.databinding.ElementSingleRvBinding;
import me.otmane.ntic.databinding.FragmentHomeBinding;
import me.otmane.ntic.models.Branch;

public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    private BranchesRecyclerViewAdapter branchesRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();

        homeViewModel.getBranches().observe(getViewLifecycleOwner(), new Observer<List<Branch>>() {
            @Override
            public void onChanged(List<Branch> branches) {
                branchesRecyclerViewAdapter.updateBranchesList(branches);
            }
        });
    }

    private void initRecyclerView() {
        branchesRecyclerViewAdapter = new BranchesRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), layoutManager.getOrientation());

        binding.branchesRV.setAdapter(branchesRecyclerViewAdapter);
        binding.branchesRV.setLayoutManager(layoutManager);
        binding.branchesRV.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class BranchesRecyclerViewAdapter
            extends RecyclerView.Adapter<BranchesRecyclerViewAdapter.ViewHolder> {
        private List<Branch> mBranches;

        public BranchesRecyclerViewAdapter() {
            this.mBranches = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateBranchesList(final List<Branch> branches) {
            this.mBranches.clear();
            this.mBranches = branches;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public BranchesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementDoubleRvBinding binding = ElementDoubleRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new BranchesRecyclerViewAdapter.ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final BranchesRecyclerViewAdapter.ViewHolder holder, int position) {
            Branch b = mBranches.get(position);
            holder.populateData(b);
            holder.addListeners(b);
        }

        @Override
        public int getItemCount() {
            return mBranches.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementDoubleRvBinding binding;

            public ViewHolder(@NonNull ElementDoubleRvBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }

            public void populateData(Branch b) {
                binding.title.setText(b.getName());
                binding.description.setText(b.getDescription());
            }

            public void addListeners(Branch b) {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle args = new Bundle();
                        args.putString(SubjectsFragment.ARG_BRANCH_ID, b.getId());

                        Navigation.findNavController(view).navigate(R.id.action_navigationHome_to_navigationSubjects, args);
                    }
                });
            }

        }
    }
}