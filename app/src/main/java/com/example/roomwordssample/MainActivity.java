package com.example.roomwordssample;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WordRoomDatabase mRoomDatabase;
    WordDao mDao;
    EditText wordEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRoomDatabase = WordRoomDatabase.getInstance(this);
        mDao = mRoomDatabase.getWordDao();

        wordEditText = findViewById(R.id.editTextWord);
        submitButton = findViewById(R.id.buttonCreate);
        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String word = wordEditText.getText().toString();

        switch (v.getId()) {
            case R.id.buttonCreate:
                insertWord(word);
                break;
            case R.id.buttonUpdate:

                break;
            case R.id.buttonRead:

                break;
            case R.id.buttonDelete:

                break;
        }
    }

    private void insertWord(String word) {
        Word newWord = new Word(word, "English");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long id = mDao.insert(newWord);
                Log.d("INSERT", "Insert at row id: " + id);
            }
        });
    }

}


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