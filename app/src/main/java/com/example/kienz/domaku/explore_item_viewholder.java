package com.example.kienz.domaku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class explore_item_viewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView_explore_item_image)
    private
    ImageView mEventImageView;
    @BindView(R.id.textView_explore_item_text)
    private
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
//                Intent intent = new Intent(mContext, RecipeDetail.class);
//                intent.putExtra("idResep",mRecipes.get(itemPosition).getRecipeId());
//                intent.putExtra("namaResep", mRecipes.get(itemPosition).getName());
//                mContext.startActivity(intent);
            }
        });
    }

    public void bindEvent(donasi event) {
        Picasso.get().load(event.gambarUrl).into(mEventImageView);
        mEventNameTextView.setText(event.getEventname());
    }
}