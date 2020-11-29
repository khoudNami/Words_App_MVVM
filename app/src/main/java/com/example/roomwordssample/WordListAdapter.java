package com.example.roomwordssample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends ListAdapter<Word, WordListAdapter.WordViewHolder> {


    private List<Word> mWords; // Cached copy of words

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recyclerview_item, parent, false);

        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");

        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Word word = getWordAtPosition(getAdapterPosition());
                    Word word = mWords.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), NewWordActivity.class);
                    intent.putExtra(NewWordActivity.WORD_ID, word.getId());
                    ((Activity) v.getContext()).startActivityForResult(intent, MainActivity.UPDATE_WORD_REQUEST_CODE);
                    Log.d("TAG", word.getWord() + " " + word.getId());
                }
            });
        }
    }

    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }

//    public static DiffUtil.ItemCallback<Word> DIFF_CALLBACK = new DiffUtil.ItemCallback<Word>() {
//        @Override
//        public boolean areItemsTheSame(Word oldItem, Word newItem) {
//            return oldItem == newItem;
//        }
//
//        @Override
//        public boolean areContentsTheSame(Word oldItem, Word newItem) {
//            return oldItem.getWord().equals(newItem.getWord());
//        }
//    };
}