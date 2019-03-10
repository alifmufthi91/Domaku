package com.example.kienz.domaku;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class explore_kategori_adapter extends RecyclerView.Adapter<explore_kategori_viewholder> {

    private ArrayList<kategori> kategori;
    private Context mContext;

    public explore_kategori_adapter(Context context, ArrayList<kategori> kategori) {
        this.mContext = context;
        this.kategori = kategori;
    }

    public explore_kategori_adapter(){
        kategori = new ArrayList<kategori>();
    }

    @Override
    public explore_kategori_viewholder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_explore, viewGroup, false);
        explore_kategori_viewholder viewHolder = new explore_kategori_viewholder(view, kategori);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(explore_kategori_viewholder holder, int position) {
        kategori recommender = kategori.get(position);

        holder.mEventNameTextView.setText(recommender.getTitle());


        holder.mEventNameRecyclerView.setLayoutManager(new LinearLayoutManager(holder
                .mEventNameRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperStart = new LinearSnapHelper();
        snapHelperStart.attachToRecyclerView(holder.mEventNameRecyclerView);
        int size = 32; // Get the offset that you want
        holder.mEventNameRecyclerView.addItemDecoration(new PaddingItemDecoration(size));

        holder.mEventNameRecyclerView.setOnFlingListener(null);

//        holder.mEventNameRecyclerView.setAdapter(new RecipeFragHomeItemsAdapter(holder.getmContext(),recommender.getRecipeList()));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
