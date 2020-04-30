package com.example.codetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mMatchId, mMatchFirstName, mMatchLastName;
    public ImageView mMatchImage;
    public MatchesViewHolders(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mMatchId = itemView.findViewById(R.id.matchId);
        mMatchFirstName = itemView.findViewById(R.id.matchFirstName);
        mMatchLastName = itemView.findViewById(R.id.matchLastName);
        mMatchImage = itemView.findViewById(R.id.matchImage);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("matchId", mMatchId.getText().toString());
        intent.putExtras(b);
        v.getContext().startActivity(intent);
    }
}
