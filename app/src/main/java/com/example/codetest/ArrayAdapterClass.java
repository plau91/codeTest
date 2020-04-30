package com.example.codetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArrayAdapterClass extends ArrayAdapter<Cards> {

    Context context;

    public ArrayAdapterClass(Context context, int resourceId, List<Cards> items) {
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cards cards = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        //TextView lastName = convertView.findViewById(R.id.name);
        ImageView image = convertView.findViewById(R.id.image);

        switch(cards.getprofileImageUrl())
        {
            case "default":
                Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
                break;
            default:
                Glide.with(convertView.getContext()).clear(image);
                Glide.with(convertView.getContext()).load(cards.getprofileImageUrl()).into(image);
                break;
        }

        name.setText(cards.getFirstName() + " " + cards.getLastName());
        //lastName.setText(cards.getLastName());
        Glide.with(getContext()).load(cards.getprofileImageUrl()).into(image);

        return convertView;
    }
}
