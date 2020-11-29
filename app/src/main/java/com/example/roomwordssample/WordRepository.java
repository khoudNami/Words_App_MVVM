package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordRepository {

    private final WordDao mWordDao;
   // private final LiveData<List<Word>> mAllWords; I really dont see the need for this field
    static final ExecutorService databaseExecutorService = Executors.newFixedThreadPool(5);

    WordRepository(Application application) {
        WordRoomDatabase roomDatabase = WordRoomDatabase.getInstance(application);
        mWordDao = roomDatabase.getWordDao();
       // mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mWordDao.getAllWords();
    }

    public void insert(Word word) {
        databaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        });
    }

    public void deleteAll() {
        databaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.deleteAll();
            }
        });
    }

    public void deleteWord(Word word) {
        databaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.deleteWord(word);
            }
        });
    }

    public LiveData<Word> getWord(int id) {
        return mWordDao.getWord(id);
    }

    public void update(Word word) {
        databaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.update(word);
            }
        });
    }
}
