package com.example.kienz.domaku.explore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.donasi;
import com.example.kienz.domaku.explore_detail;
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
                Intent intent = new Intent(mContext, explore_detail.class);
//                intent.putExtra("idResep",mRecipes.get(itemPosition).getRecipeId());
//                intent.putExtra("namaResep", mRecipes.get(itemPosition).getName());
                mContext.startActivity(intent);
            }
        });
    }

    public void bindEvent(donasi event) {
        Log.d("namonna",event.getEventname());
        Picasso.get().load(event.getGambarUrl()).into(mEventImageView);
        mEventNameTextView.setText(event.getEventname());
    }
}
