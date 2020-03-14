package com.example.android_recyclerview_sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_recyclerview_sample.R;
import com.example.android_recyclerview_sample.model.Sandwich;

import java.util.ArrayList;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder> {

    ArrayList<Sandwich> data = new ArrayList<>();

    public void setData(ArrayList<Sandwich> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_sandwich, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sandwich item = data.get(position);
        holder.textName.setText(item.getMainName());
        holder.textDescription.setText(item.getDescription());

        Glide.with(holder.imageView.getContext())
                .load(item.getImage())
                .apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(16))).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // TODO (02) Create SandwichAdapter class and extend it
// from RecyclerView.Adapter<TextItemViewHolder>

// TODO (03) Create a variable, data, that holds a list of Sandwich.

// TODO (04) Override getItemCount() to return the total number of items in the data set.

// TODO (05) Override onBindViewHolder() and have it update the contents of the
// ViewHolder to reflect the item at the given position.

// TODO (06) Override onCreateViewHolder()

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textName;
        private TextView textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

//RecyclerView Widget
//Layout list or grid
//Scroll horizontal or vertical


//Displaying large lists of data
//It uses adapter pattern


/**
 * Adapters
 * converts one interface to work with another
 * RecyclerView adapter must provide a few methods for RecyclerView to understand how to display data on the screen
 * 1. How many items are available
 * 2. How to draw an item
 * 3. Create a new view
 */

/**
 * ViewHolder
 * Hold views
 * Store information for recycler view
 */

