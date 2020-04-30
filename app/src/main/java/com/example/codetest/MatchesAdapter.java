package com.example.codetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders>
{
    private List<MatchesObject> matchesList;
    private Context context;


    public MatchesAdapter(List<MatchesObject> matchesList, Context context)
    {
        this.matchesList = matchesList;
        this.context = context;
    }

    @Override
    public MatchesViewHolders onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(lp);
        MatchesViewHolders rev = new MatchesViewHolders(layoutView);

        return rev;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolders holder, int position) {

        holder.mMatchId.setText(matchesList.get(position).getUserId());
        holder.mMatchFirstName.setText(matchesList.get(position).getFirstName());
        holder.mMatchLastName.setText(matchesList.get(position).getLastName());
        //if(!matchesList.get(position).getProfileImageUrl().equals("default"))
        //{
            Glide.with(context).load(matchesList.get(position).getProfileImageUrl()).into(holder.mMatchImage);
        //}
    }

    @Override
    public int getItemCount()
    {
        return this.matchesList.size();
    }
}
