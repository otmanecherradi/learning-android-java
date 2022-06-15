package me.otmane.ntic.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.databinding.ElementColleagueRvBinding;
import me.otmane.ntic.databinding.FragmentColleaguesBinding;
import me.otmane.ntic.dtos.GroupsDTOs;
import me.otmane.ntic.models.Student;

public class ColleaguesFragment extends Fragment {
    public static final String TAG = "ColleaguesFragment";

    private FragmentColleaguesBinding binding;
    private ProfileViewModel profileViewModel;
    private ColleaguesRecyclerViewAdapter colleaguesRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentColleaguesBinding.inflate(inflater, container, false);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();

        profileViewModel.getColleagues().observe(getViewLifecycleOwner(), new Observer<GroupsDTOs.GroupStudentsResponseDTO>() {
            @Override
            public void onChanged(GroupsDTOs.GroupStudentsResponseDTO students) {
                Log.d(TAG, "onChanged: " + students);
                colleaguesRecyclerViewAdapter.updateStudentsList(students);
            }
        });
    }

    private void initRecyclerView() {
        colleaguesRecyclerViewAdapter = new ColleaguesRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);

        binding.colleaguesRV.setAdapter(colleaguesRecyclerViewAdapter);
        binding.colleaguesRV.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class ColleaguesRecyclerViewAdapter
            extends RecyclerView.Adapter<ColleaguesRecyclerViewAdapter.ViewHolder> {
        private List<Student> mStudents;

        public ColleaguesRecyclerViewAdapter() {
            this.mStudents = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateStudentsList(final List<Student> students) {
            this.mStudents.clear();
            this.mStudents = students;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementColleagueRvBinding binding = ElementColleagueRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Student s = mStudents.get(position);

            holder.populateData(s);
            holder.addListeners(s);
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementColleagueRvBinding binding;

            public ViewHolder(@NonNull ElementColleagueRvBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }

            public void populateData(Student s) {
                binding.name.setText(s.getFullName());
            }

            public void addListeners(Student s) {

            }

        }
    }
}