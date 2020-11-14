package com.example.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    private String mLanguage;

    @Ignore
    public Word(@NonNull String word, String language) {
        this.mWord = word;
        this.mLanguage = language;
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

    public void setLanguage(String language) {
        mLanguage = language;
    }
}
