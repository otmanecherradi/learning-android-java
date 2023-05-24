package me.otmane.b3.project.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import me.otmane.b3.project.models.Question;
import me.otmane.b3.project.models.Quiz;

public class QuizViewModel extends ViewModel {
    private final MutableLiveData<List<Quiz>> quizes = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Question>> questions = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<HashMap<String, Boolean>> quizResult = new MutableLiveData<>(new HashMap<>());
    private final MutableLiveData<Integer> currentPosition = new MutableLiveData<>(0);
    private final MutableLiveData<Question> currentQuestion = new MutableLiveData<>(null);

    public MutableLiveData<List<Quiz>> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes.postValue(quizes);
    }

    public MutableLiveData<List<Question>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.postValue(questions);
    }

    public void resetResponses() {
        quizResult.setValue(new HashMap<>());
    }

    public void addResponse(String qtsId, Boolean res) {
        HashMap<String, Boolean> state = quizResult.getValue();
        Objects.requireNonNull(state).put(qtsId, res);
        quizResult.setValue(state);
    }

    public MutableLiveData<HashMap<String, Boolean>> getQuizResult() {
        return quizResult;
    }

    public void startQuiz() {
        currentPosition.setValue(0);
        switchQuestion();
    }

    public boolean hasNext() {
        List<Question> qts = questions.getValue();
        return currentPosition.getValue() < (qts.size() - 1);
    }

    public void nextQuestion() {
        currentPosition.setValue(currentPosition.getValue() + 1);
        switchQuestion();
    }

    public void switchQuestion() {
        List<Question> qts = questions.getValue();
        Objects.requireNonNull(qts);
        currentQuestion.postValue(qts.get(currentPosition.getValue()));
    }

    public Question getCurrentQuestion() {
        return currentQuestion.getValue();
    }

    public MutableLiveData<Question> getCurrentQuestionData() {
        return currentQuestion;
    }
}
