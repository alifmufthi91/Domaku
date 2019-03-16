package com.example.kienz.domaku.ambil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.kienz.domaku.R;
import com.example.kienz.domaku.model.mengambil;

import java.util.ArrayList;

public class ambil_item_adapter extends RecyclerView.Adapter<ambil_item_viewholder> {
    private ArrayList<mengambil> mAmbil;
    private Context mContext;


    public ambil_item_adapter(Context context, ArrayList<mengambil> donasi) {
        mContext = context;
        mAmbil = donasi;

        Log.d("namonni", String.valueOf(mAmbil.size()));
    }


    @Override
    public ambil_item_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ambil, parent, false);
        ambil_item_viewholder viewHolder = new ambil_item_viewholder(view, mAmbil);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ambil_item_viewholder holder, int position) {
        holder.bindEvent(mAmbil.get(position));
    }

    @Override
    public int getItemCount() {
        return mAmbil.size();
    }


}