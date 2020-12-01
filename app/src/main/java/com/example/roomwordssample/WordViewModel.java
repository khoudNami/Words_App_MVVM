package com.example.roomwordssample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository mRepository;
    private final LiveData<List<Word>> mAllWords;
    private final LiveData<PagedList<Word>> mPagedListLiveData;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mPagedListLiveData = mRepository.getAllWordsPaging();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    LiveData<PagedList<Word>> getPagedListLiveData() {
        return mPagedListLiveData;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteWord(Word word) {
        mRepository.deleteWord(word);
    }

    public LiveData<Word> getWord(int id) {
        return mRepository.getWord(id);
    }

    public void update(Word word) {
        mRepository.update(word);
    }
}
