package com.example.roomwordssample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao getWordDao();

    private static WordRoomDatabase INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    WordRepository.databaseExecutorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            String[] words = {"ubaba", "umama", "ingane"};
                            WordDao wordDao = INSTANCE.getWordDao();
                            if (wordDao.getAnyWord().length < 1) {
                                for (int i = 0; i < words.length; i++) {
                                    Word word = new Word(words[i]);
                                    wordDao.insert(word);
                                }
                            }
                        }
                    });
                }
            };

    static WordRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "words_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}