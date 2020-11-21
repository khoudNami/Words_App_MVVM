package com.example.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;
    static final ExecutorService databaseExecuterService = Executors.newFixedThreadPool(5);
    Word[] words;

    WordRepository(Application application) {
        WordRoomDatabase roomDatabase = WordRoomDatabase.getInstance(application);
        mWordDao = roomDatabase.getWordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        databaseExecuterService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        });
    }

    public Word[] getAnyWord() {
        databaseExecuterService.execute(new Runnable() {
            @Override
            public void run() {
                words = mWordDao.getAnyWord();
            }
        });
        return words;
    }
}
