package com.example.flashcardquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFlashcardActivity extends AppCompatActivity {

    private EditText etQuestion, etAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        etQuestion = findViewById(R.id.et_question);
        etAnswer = findViewById(R.id.et_answer);
        Button btnSaveFlashcard = findViewById(R.id.btn_save_flashcard);

        btnSaveFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = etQuestion.getText().toString().trim();
                String answer = etAnswer.getText().toString().trim();

                if (!question.isEmpty() && !answer.isEmpty()) {
                    FlashcardDatabase.getInstance(AddFlashcardActivity.this).addFlashcard(question, answer);
                    Toast.makeText(AddFlashcardActivity.this, "Flashcard Saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddFlashcardActivity.this, "Please fill out both fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
