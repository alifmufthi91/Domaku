package com.example.kienz.domaku.explore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.model.kategori;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class explore_kategori_viewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_kategori_title) TextView mEventNameTextView;
    @BindView(R.id.recyclerView_kategori_items) RecyclerView mEventRecyclerView;
    Context mContext;
    ArrayList<kategori> mKategori = new ArrayList<>();

    public explore_kategori_viewholder(View itemView, ArrayList<kategori> events) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mKategori = events;
    }

    public Context getmContext() {
        return mContext;
    }

    public TextView getmEventNameTextView() {
        return mEventNameTextView;
    }

    public RecyclerView getmEventNameRecyclerView() {
        return mEventRecyclerView;
    }

    public ArrayList<kategori> getmKategori() {
        return mKategori;
    }
}
