package me.otmane.b3.project.ui.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentQuizBinding;
import me.otmane.b3.project.models.Quiz;
import me.otmane.b3.project.repositories.QuizRepository;

public class QuizFragment extends Fragment {
    public static final String QUIZ_KEY = "QUIZ_KEY";

    private FragmentQuizBinding binding;
    private QuizViewModel mModel;

    private Quiz mQuiz;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);

        mQuiz = requireArguments().getSerializable(QUIZ_KEY, Quiz.class);

        mModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().setTitle(mQuiz.getTitle());

        QuizRepository.getQuizQuestionList(mQuiz.getId(), mModel);

        populateData();
        initializeEvents();
    }

    private void populateData() {

    }

    private void initializeEvents() {
        NavController navController = NavHostFragment.findNavController(QuizFragment.this);
        binding.startBtn.setOnClickListener(v -> {
            mModel.startQuiz();
            navController.navigate(R.id.action_quizFragment_to_questionFragment);
        });
    }

}