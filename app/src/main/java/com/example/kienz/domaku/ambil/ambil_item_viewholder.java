package com.example.kienz.domaku.ambil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.model.donasi;
import com.example.kienz.domaku.model.mengambil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
    private ArrayList<mengambil> mDonasi = new ArrayList<>();


    public ambil_item_viewholder(View itemView, ArrayList<mengambil> donasiList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mDonasi = donasiList;
        mAmbilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, explore_detailActivity.class);
//                intent.putExtra("idResep",mRecipes.get(itemPosition).getRecipeId());
//                intent.putExtra("namaResep", mRecipes.get(itemPosition).getName());
//                mContext.startActivity(intent);
            }
        });
    }

    public void bindEvent(mengambil event) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDonasiReference = mFirebaseDatabase.getReference().child("Donasi/"+event.donasiId);
        mDonasiReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donasi item = dataSnapshot.getValue(donasi.class);
                Picasso.get().load(item.getGambar()).into(mAmbilImageView);
                mAmbilNameTextView.setText(item.getJudul());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
