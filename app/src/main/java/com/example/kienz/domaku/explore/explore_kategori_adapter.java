package com.example.kienz.domaku.explore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.domaku.PaddingItemDecoration;
import com.example.kienz.domaku.R;
import com.example.kienz.domaku.kategori;

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
        Log.d("masawo",recommender.getTitle());


        holder.mEventRecyclerView.setLayoutManager(new LinearLayoutManager(holder
                .mEventRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperStart = new LinearSnapHelper();
        snapHelperStart.attachToRecyclerView(holder.mEventRecyclerView);
        int size = 6; // Get the offset that you want
        holder.mEventRecyclerView.addItemDecoration(new PaddingItemDecoration(size));

        holder.mEventRecyclerView.setOnFlingListener(null);

        holder.mEventRecyclerView.setAdapter(new explore_item_adapter(holder.getmContext(),recommender.getEventList()));

    }

    @Override
    public int getItemCount() {
        return this.kategori.size();
    }
}
