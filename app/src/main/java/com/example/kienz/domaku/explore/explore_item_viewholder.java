package com.example.kienz.domaku.explore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.explore_detailActivity;
import com.example.kienz.domaku.model.donasi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class explore_item_viewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_explore_item_image)
    ImageView mEventImageView;
    @BindView(R.id.textView_explore_item_text)
    TextView mEventNameTextView;
    private Context mContext;
    private ArrayList<donasi> mDonasi = new ArrayList<>();


    public explore_item_viewholder(View itemView, ArrayList<donasi> donasiList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mDonasi = donasiList;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, explore_detailActivity.class);
                intent.putExtra("idDonasi",mDonasi.get(itemPosition).getId());
                mContext.startActivity(intent);
            }
        });
    }

    public void bindEvent(donasi event) {
        Picasso.get().load(event.getGambar()).into(mEventImageView);
        mEventNameTextView.setText(event.getJudul());
    }
}
