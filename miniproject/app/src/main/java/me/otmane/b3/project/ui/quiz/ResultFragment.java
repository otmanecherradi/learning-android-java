package me.otmane.b3.project.ui.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {


    private FragmentResultBinding binding;
    private QuizViewModel mModel;

    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);

        mModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        requireActivity().setTitle("Results");

        HashMap<String, Boolean> map = mModel.getQuizResult().getValue();

        AtomicInteger correct = new AtomicInteger();
        map.entrySet().forEach(kv -> {
            if (kv.getValue())
                correct.addAndGet(1);
        });

        populateData(correct);
        initiateEvents();
    }

    private void populateData(AtomicInteger correct) {
        int all = mModel.getQuizResult().getValue().size();
        if (correct.get() <= (all / 2)) {
            binding.congrats.setText("Good luck next time");
            binding.score.setText(String.format("Your score is: %s / %s", correct.get(), all));
        } else {
            binding.congrats.setText("Congrats, NICE");
            binding.score.setText(String.format("Your score is: %s / %s", correct.get(), all));
        }
    }

    private void initiateEvents() {
        binding.goBack.setOnClickListener(v -> navController.popBackStack(R.id.dashboardFragment, false));
    }
}