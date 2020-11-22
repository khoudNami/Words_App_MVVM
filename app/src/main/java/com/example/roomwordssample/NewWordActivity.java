package com.example.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";
    public static final String WORD_ID = "com.example.android.roomwordssample.NOTE_ID";

    private EditText mEditWordView;
    WordViewModel mViewModel;
    Intent mReceivedIntent;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);

        mViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mReceivedIntent = getIntent();

        mId = mReceivedIntent.getIntExtra(WORD_ID, -1);

        if (mId != -1) {
            mViewModel.getWord(mId).observe(this, new Observer<Word>() {
                @Override
                public void onChanged(Word word) {
                    mEditWordView.setText(word.getWord());
                }
            });
        }


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {

            private String mWord;

            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else if (mId != -1) {//signal for update request
                    mWord = mEditWordView.getText().toString();
                    replyIntent.putExtra(WORD_ID, mId);
                    replyIntent.putExtra(MainActivity.WORD, mWord);
                    setResult(RESULT_OK, replyIntent);
                } else {//signal for insert word request
                    mWord = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, mWord);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}