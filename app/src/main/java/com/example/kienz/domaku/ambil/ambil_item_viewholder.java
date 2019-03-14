package com.example.kienz.domaku.ambil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.donasi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ambil_item_viewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.ambil_gambar)
    ImageView mAmbilImageView;
    @BindView(R.id.ambil_judul)
    TextView mAmbilNameTextView;
    @BindView(R.id.ambil_button_ambil)
    Button mAmbilButton;
    private Context mContext;
    private ArrayList<donasi> mDonasi = new ArrayList<>();


    public ambil_item_viewholder(View itemView, ArrayList<donasi> donasiList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mDonasi = donasiList;
        mAmbilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, explore_detail.class);
//                intent.putExtra("idResep",mRecipes.get(itemPosition).getRecipeId());
//                intent.putExtra("namaResep", mRecipes.get(itemPosition).getName());
//                mContext.startActivity(intent);
            }
        });
    }

    public void bindEvent(donasi event) {
        Log.d("namonna",event.getJudul());
        Picasso.get().load(event.getGambar()).into(mAmbilImageView);
        mAmbilNameTextView.setText(event.getJudul());
    }
}
