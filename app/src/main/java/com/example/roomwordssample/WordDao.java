package com.example.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Delete
    void deleteWord(Word word);

    @Update
    void update(Word word);

//    @Update
//    void update(Word... word);//variable number of params

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM word_table LIMIT 1")
    Word[] getAnyWord();

    @Query("SELECT * FROM word_table WHERE id=:id ")
    LiveData<Word> getWord(int id);

}
