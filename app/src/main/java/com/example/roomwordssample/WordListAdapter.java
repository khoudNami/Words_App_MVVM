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
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends PagedListAdapter<Word, WordListAdapter.WordViewHolder> {

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
        Word word = getItem(position);
        if (word != null) {
            holder.wordItemView.setText(word.getWord());
        } else {
            holder.wordItemView.setText("Helllp");
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Word word = getWordAtPosition(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), NewWordActivity.class);
                    intent.putExtra(NewWordActivity.WORD_ID, word.getId());
                    ((Activity) v.getContext()).startActivityForResult(intent, MainActivity.UPDATE_WORD_REQUEST_CODE);
                    Log.d("TAG", word.getWord() + " " + word.getId());
                }
            });
        }
    }

    public Word getWordAtPosition(int position) {
        return getItem(position);
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.equals(newItem);
        }
    }

}