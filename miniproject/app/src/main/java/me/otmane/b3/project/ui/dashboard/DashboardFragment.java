package me.otmane.b3.project.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentDashboardBinding;
import me.otmane.b3.project.models.Quiz;
import me.otmane.b3.project.repositories.QuizRepository;
import me.otmane.b3.project.ui.quiz.QuizViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private QuizViewModel quizModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        quizModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().setTitle("Quizes");

        initializeRecyclerView();
        initializeEvents();
    }


    private void initializeRecyclerView() {
        QuizRepository.getQuizList(quizModel);

        QuizesRecyclerViewAdapter recyclerViewAdapter = new QuizesRecyclerViewAdapter(new ArrayList<>(), NavHostFragment.findNavController(DashboardFragment.this));
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rv.setAdapter(recyclerViewAdapter);
        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        divider.setDividerInsetStart(16);
        divider.setDividerInsetEnd(16);
        binding.rv.addItemDecoration(divider);

        quizModel.getQuizes().observe(getViewLifecycleOwner(), recyclerViewAdapter::updateQuizList);
    }

    private void initializeEvents() {
    }
}