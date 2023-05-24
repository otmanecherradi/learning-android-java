package me.otmane.b3.project.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentQuestionBinding;
import me.otmane.b3.project.models.Question;
import me.otmane.b3.project.models.Quiz;
import me.otmane.b3.project.repositories.QuizRepository;

public class QuestionFragment extends Fragment {
    private FragmentQuestionBinding binding;
    private NavController navController;
    private QuizViewModel mModel;
    private QuestionsRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);

        mModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(QuestionFragment.this);

        mAdapter = new QuestionsRecyclerViewAdapter(navController, mModel);

        initiateRV();
        populateData();
        initiateEvents();
    }


    private void initiateRV() {
        binding.optionRV.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.optionRV.setAdapter(mAdapter);
    }

    private void populateData() {
        mModel.getCurrentQuestionData().observe(getViewLifecycleOwner(), question -> {
            binding.questionText.setText(question.getTitle());
            mAdapter.updateQuestionList(question.getOptions());
        });
    }

    private void initiateEvents() {
        binding.cancelBtn.setOnClickListener(v -> {
            mModel.resetResponses();
            navController.popBackStack(R.id.quizFragment, false);
        });

        binding.nextBtn.setOnClickListener(v -> {
            if (mModel.getQuizResult().getValue().get(mModel.getCurrentQuestion().getId()) != null) {
                if (mModel.hasNext())
                    mModel.nextQuestion();
                else
                    navController.navigate(R.id.action_questionFragment_to_resultFragment);
            } else {
                Snackbar.make(binding.getRoot(), "Choose a response to continue", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
