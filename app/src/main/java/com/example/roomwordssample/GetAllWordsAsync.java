package com.example.roomwordssample;

import android.os.AsyncTask;

import java.util.List;

public class GetAllWordsAsync extends AsyncTask<Void, Void, List<Word>> {
    WordDao mDao;
    List<Word>mWordList;

    public GetAllWordsAsync(WordDao dao,List<Word>wordList) {
        mDao = dao;
        mWordList=wordList;
    }

    @Override
    protected void onPostExecute(List<Word> words) {
        super.onPostExecute(words);
        mWordList=words;

    }

    @Override
    protected List<Word> doInBackground(Void... voids) {
        return mDao.getAllWords();
    }
}
