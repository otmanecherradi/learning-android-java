package me.otmane.ntic.ui.exams;

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

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import me.otmane.ntic.databinding.ElementExamRvBinding;
import me.otmane.ntic.databinding.FragmentExamsBinding;
import me.otmane.ntic.models.Exam;

public class ExamsFragment extends Fragment {
    public static final String TAG = "ExamsFragment";

    private FragmentExamsBinding binding;
    private ExamsViewModel examsViewModel;
    private ExamsRecyclerViewAdapter examsRecyclerViewAdapter;

    public static ExamsFragment newInstance() {
        return new ExamsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentExamsBinding.inflate(getLayoutInflater(), viewGroup, false);
        examsViewModel = new ViewModelProvider(this).get(ExamsViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initListeners();
        examsViewModel.getExams().observe(getViewLifecycleOwner(), new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                examsRecyclerViewAdapter.updateSchoolsList(exams);
            }
        });
    }

    private void initRecyclerView() {
        examsRecyclerViewAdapter = new ExamsRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), layoutManager.getOrientation());

        binding.examsRV.setAdapter(examsRecyclerViewAdapter);
        binding.examsRV.setLayoutManager(layoutManager);
        binding.examsRV.addItemDecoration(dividerItemDecoration);
    }

    private void initListeners() {

    }

    public static class ExamsRecyclerViewAdapter
            extends RecyclerView.Adapter<ExamsRecyclerViewAdapter.ViewHolder> {
        private List<Exam> mExams;

        public ExamsRecyclerViewAdapter() {
            this.mExams = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateSchoolsList(final List<Exam> exams) {
            this.mExams.clear();
            this.mExams = exams;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ExamsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementExamRvBinding binding = ElementExamRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new ExamsRecyclerViewAdapter.ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final ExamsRecyclerViewAdapter.ViewHolder holder, int position) {
            Exam e = mExams.get(position);
            holder.populateData(e);
            holder.addListeners(e);
        }

        @Override
        public int getItemCount() {
            return mExams.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementExamRvBinding binding;

            public ViewHolder(@NonNull ElementExamRvBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }

            public void populateData(Exam e) {
                binding.subject.setText(e.getGroupReference().getSubject().getName());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                binding.date.setText(simpleDateFormat.format(e.getDate().toString()));
            }

            public void addListeners(Exam e) {

            }

        }
    }
}