package me.otmane.b3.project.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import me.otmane.b3.project.models.Option;
import me.otmane.b3.project.models.Question;
import me.otmane.b3.project.models.Quiz;
import me.otmane.b3.project.ui.quiz.QuizViewModel;

public class QuizRepository {
    private static final String QUIZ_COLLECTION_NAME = "quizes";
    private static final String QUESTION_COLLECTION_NAME = "questions";

    public static void getQuizList(QuizViewModel quizModel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection(QUIZ_COLLECTION_NAME)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Quiz> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(new Quiz(document.getId(), document.getString("title")));
                        }
                        quizModel.setQuizes(list);
                    }
                });

    }

    public static void getQuizQuestionList(String quizId, QuizViewModel quizModel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection(QUIZ_COLLECTION_NAME)
                .document(quizId)
                .collection(QUESTION_COLLECTION_NAME)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Question> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            List<HashMap<String, ?>> options = (List<HashMap<String, ?>>) document.get("options");
                            list.add(
                                    new Question(
                                            document.getId(),
                                            document.getString("title"),
                                            document.get("order", Integer.class),
                                            Objects.requireNonNull(options)
                                                    .stream()
                                                    .map(Option::mapToObject)
                                                    .collect(Collectors.toList())
                                    )
                            );
                        }

                        list.sort(Question.questionComparator);
                        quizModel.setQuestions(list);
                    }
                });

    }
}
