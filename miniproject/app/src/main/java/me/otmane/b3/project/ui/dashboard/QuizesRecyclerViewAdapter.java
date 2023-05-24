package me.otmane.b3.project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentQuizItemBinding;
import me.otmane.b3.project.models.Quiz;
import me.otmane.b3.project.ui.quiz.QuizFragment;

import java.util.List;


public class QuizesRecyclerViewAdapter extends RecyclerView.Adapter<QuizesRecyclerViewAdapter.ViewHolder> {
    private final NavController navController;
    private List<Quiz> mValues;

    public QuizesRecyclerViewAdapter(List<Quiz> items, NavController navController) {
        mValues = items;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentQuizItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Quiz q = mValues.get(position);

        holder.populateData(q);
        holder.initEvents(q, navController);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateQuizList(final List<Quiz> quizList) {
        this.mValues.clear();
        this.mValues = quizList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FragmentQuizItemBinding binding;
        public ViewHolder(FragmentQuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void populateData(Quiz quiz) {
            binding.title.setText(quiz.getTitle());
        }

        public void initEvents(Quiz q, NavController navController) {
            binding.getRoot().setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable(QuizFragment.QUIZ_KEY, q);
                navController.navigate(R.id.action_dashboardFragment_to_quizFragment, bundle);
            });
        }
    }
}