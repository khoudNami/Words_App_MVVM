package com.example.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyWordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;
    public MyWordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
        wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Word word = mWords.get(getAdapterPosition());
//                Intent intent = new Intent(v.getContext(), NewWordActivity.class);
//                intent.putExtra(NewWordActivity.WORD_ID, word.getId());
//                ((Activity) v.getContext()).startActivityForResult(intent, MainActivity.UPDATE_WORD_REQUEST_CODE);
//                Log.d("TAG", word.getWord() + " " + word.getId());
            }
        });
    }
}
