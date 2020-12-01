package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordRepository {

    private final WordDao mWordDao;
    static final ExecutorService databaseExecutorService = Executors.newFixedThreadPool(5);

    private final LiveData<PagedList<Word>> mPagedListLiveData;

    WordRepository(Application application) {
        WordRoomDatabase roomDatabase = WordRoomDatabase.getInstance(application);
        mWordDao = roomDatabase.getWordDao();
        mPagedListLiveData = new LivePagedListBuilder<>(mWordDao.getAllWordsPaging(), 20).build();
    }

    LiveData<List<Word>> getAllWords() {
        return mWordDao.getAllWords();
    }

    LiveData<PagedList<Word>> getAllWordsPaging() {
        return mPagedListLiveData;
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
