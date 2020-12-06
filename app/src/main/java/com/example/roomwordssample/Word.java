package com.example.roomwordssample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private final String mWord;

    private String mLanguage;

    @Ignore
    public Word(int id, @NonNull String word) {
        this.mWord = word;
        this.id = id;
    }

    public Word(@NonNull String word) {
        mWord = word;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public int getId() {
        return id;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;

        Word word = (Word) obj;
        return word.id == this.id
                && word.mWord == this.mWord
                && word.mLanguage == this.mLanguage;
    }
}
