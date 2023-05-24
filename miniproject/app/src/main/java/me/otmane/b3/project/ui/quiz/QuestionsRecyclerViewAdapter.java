package me.otmane.b3.project.ui.quiz;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.otmane.b3.project.R;
import me.otmane.b3.project.databinding.FragmentQuestionItemBinding;
import me.otmane.b3.project.models.Option;
import me.otmane.b3.project.models.Question;


public class QuestionsRecyclerViewAdapter extends RecyclerView.Adapter<QuestionsRecyclerViewAdapter.ViewHolder> {
    private final NavController navController;
    private List<Option> mValues = new ArrayList<>();

    private final QuizViewModel mModel;

    int selectedPosition = -1;
    int lastSelectedPosition = -1;

    public QuestionsRecyclerViewAdapter(NavController navController, QuizViewModel mModel) {
        this.mModel = mModel;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentQuestionItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Option q = mValues.get(position);


        if (selectedPosition == holder.getBindingAdapterPosition()) {
            holder.binding.getRoot().setBackgroundResource(R.drawable.bg_quiz_option_selected);
        } else {
            holder.binding.getRoot().setBackgroundResource(R.drawable.bg_quiz_option);
        }

        holder.populateData(holder, selectedPosition, q);

        holder.binding.getRoot().setOnClickListener(v -> {
            lastSelectedPosition = selectedPosition;
            selectedPosition = holder.getBindingAdapterPosition();
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);

            Question currentQuestion = mModel.getCurrentQuestion();
            mModel.addResponse(currentQuestion.getId(), q.isCorrect());
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateQuestionList(final List<Option> quizList) {
        this.mValues.clear();
        this.mValues = quizList;
        notifyDataSetChanged();

        lastSelectedPosition = -1;
        selectedPosition = -1;
        notifyItemChanged(lastSelectedPosition);
        notifyItemChanged(selectedPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FragmentQuestionItemBinding binding;

        public ViewHolder(FragmentQuestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void populateData(ViewHolder holder, int selectedPosition, Option q) {
            binding.text.setText(q.getTitle());
        }

        public void initEvents(Option q, QuizViewModel mModel, NavController navController) {

        }
    }
}