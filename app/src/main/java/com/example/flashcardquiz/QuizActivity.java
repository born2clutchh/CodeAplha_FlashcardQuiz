package com.example.flashcardquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private EditText etAnswer;
    private Button btnSubmit;
    private List<Flashcard> flashcards;
    private int currentFlashcardIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tv_question);
        tvScore = findViewById(R.id.tv_score);
        etAnswer = findViewById(R.id.et_answer);
        btnSubmit = findViewById(R.id.btn_submit);

        flashcards = FlashcardDatabase.getInstance(this).getAllFlashcards();

        if (flashcards.size() == 0) {
            Toast.makeText(this, "No flashcards available. Please add some flashcards first.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            showNextFlashcard();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void showNextFlashcard() {
        if (currentFlashcardIndex < flashcards.size()) {
            Flashcard currentFlashcard = flashcards.get(currentFlashcardIndex);
            tvQuestion.setText("Question : "+ currentFlashcard.getQuestion());
            etAnswer.setText("");
        } else {
            Toast.makeText(this, "Quiz finished! Your score: " + score, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void checkAnswer() {
        String userAnswer = etAnswer.getText().toString().trim();
        Flashcard currentFlashcard = flashcards.get(currentFlashcardIndex);

        if (userAnswer.equalsIgnoreCase(currentFlashcard.getAnswer())) {
            score++;
        }

        tvScore.setText("Score: " + score);
        currentFlashcardIndex++;
        showNextFlashcard();
    }
}
