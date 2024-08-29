package com.example.flashcardquiz;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class FlashcardDatabase {

    private static FlashcardDatabase instance;
    private List<Flashcard> flashcards;

    private FlashcardDatabase(Context context) {
        flashcards = new ArrayList<>();
    }

    public static FlashcardDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new FlashcardDatabase(context);
        }
        return instance;
    }

    public void addFlashcard(String question, String answer) {
        flashcards.add(new Flashcard(question, answer));
    }

    public List<Flashcard> getAllFlashcards() {
        return flashcards;
    }
}
