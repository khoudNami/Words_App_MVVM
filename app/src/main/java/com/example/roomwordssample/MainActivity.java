package com.example.roomwordssample;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NUMBER_OF_THREADS = 4;
    WordRoomDatabase mRoomDatabase;
    WordDao mDao;
    EditText wordEditText;
    Button createButton, readButton, updateButton, deleteButton;
    List<Word> listOfWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRoomDatabase = WordRoomDatabase.getInstance(this);
        mDao = mRoomDatabase.getWordDao();

        findViewsById();

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        createButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    private void findViewsById() {
        wordEditText = findViewById(R.id.editTextWord);
        createButton = findViewById(R.id.buttonCreate);
        readButton = findViewById(R.id.buttonRead);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonRead);
    }

    @Override
    public void onClick(View v) {
        String word = wordEditText.getText().toString();

        switch (v.getId()) {
            case R.id.buttonCreate:
                insertWord(word);
                break;

            case R.id.buttonRead:
                readAndDisplayAllWords();
                break;

            case R.id.buttonUpdate:
                break;


            case R.id.buttonDelete:
                break;
        }
    }

    private void readAndDisplayAllWords() {
        mDao.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> list) {
                //listOfWords = list;
                for (int i = 0; i < list.size(); i++) {
                    Log.d("getAllWords", "Word: " + list.get(i).getWord() + " Language: " + list.get(i).getLanguage());
                }
            }
        });
    }

    private void insertWord(String word) {
        Word newWord = new Word(word, "English");

        WordRoomDatabase.databaseReadWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long position = mDao.insert(newWord);
                Log.d("INSERT", "Inserted: " + newWord.getWord() + " " + newWord.getLanguage() + " at position: " + position);
            }
        });

    }

}


//        WordRoomDatabase.databaseReadWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                mDao.insert(newWord);
//                Log.d("INSERT", "Inserted: " + newWord.getWord() + " " + newWord.getLanguage());
//            }
//        });

//    FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//        }
//    });

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }