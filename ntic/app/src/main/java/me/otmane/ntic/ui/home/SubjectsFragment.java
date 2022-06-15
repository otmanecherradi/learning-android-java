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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.databinding.ElementDoubleRvBinding;
import me.otmane.ntic.databinding.ElementSingleRvBinding;
import me.otmane.ntic.databinding.FragmentSubjectsBinding;
import me.otmane.ntic.models.BranchSubject;

public class SubjectsFragment extends Fragment {
    public static final String TAG = "SubjectsFragment";

    public static final String ARG_BRANCH_ID = "branchId";
    private String branchId = null;

    private FragmentSubjectsBinding binding;
    private HomeViewModel homeViewModel;

    private SubjectsRecyclerViewAdapter subjectsRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            branchId = getArguments().getString(ARG_BRANCH_ID);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSubjectsBinding.inflate(inflater, container, false);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();

        homeViewModel.getBranchSubjects(branchId).observe(getViewLifecycleOwner(), new Observer<List<BranchSubject>>() {
            @Override
            public void onChanged(List<BranchSubject> branchSubjects) {
                subjectsRecyclerViewAdapter.updateBranchesList(branchSubjects);
            }
        });
    }

    private void initRecyclerView() {
        subjectsRecyclerViewAdapter = new SubjectsRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), layoutManager.getOrientation());

        binding.subjectsRV.setAdapter(subjectsRecyclerViewAdapter);
        binding.subjectsRV.setLayoutManager(layoutManager);
        binding.subjectsRV.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class SubjectsRecyclerViewAdapter
            extends RecyclerView.Adapter<SubjectsRecyclerViewAdapter.ViewHolder> {
        private List<BranchSubject> mBranchSubjects;

        public SubjectsRecyclerViewAdapter() {
            this.mBranchSubjects = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateBranchesList(final List<BranchSubject> subjects) {
            this.mBranchSubjects.clear();
            this.mBranchSubjects = subjects;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public SubjectsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementDoubleRvBinding binding = ElementDoubleRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new SubjectsRecyclerViewAdapter.ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final SubjectsRecyclerViewAdapter.ViewHolder holder, int position) {
            BranchSubject bs = mBranchSubjects.get(position);
            holder.populateData(bs);
            holder.addListeners(bs);
        }

        @Override
        public int getItemCount() {
            return mBranchSubjects.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementDoubleRvBinding binding;

            public ViewHolder(@NonNull ElementDoubleRvBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }

            public void populateData(BranchSubject bs) {
                binding.title.setText(bs.getSubject().getName());
                binding.description.setText(bs.getSubject().getDescription());
            }

            public void addListeners(BranchSubject bs) {

            }

        }
    }
}