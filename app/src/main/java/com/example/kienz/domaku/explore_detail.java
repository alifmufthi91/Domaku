package com.example.kienz.domaku;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class explore_detail extends AppCompatActivity {

    @BindView(R.id.explore_detail_back)
    ImageButton backButton;
    @BindView(R.id.explore_detail_photo)
    ImageView detailFoto;
    @BindView(R.id.explore_detail_judul)
    TextView detailJudul;
    donasi detailDonasi;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mItemDonasiDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);
        String id = getIntent().getStringExtra("idDonasi");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mItemDonasiDatabaseReference = mFirebaseDatabase.getReference().child("Donasi/"+id);
        mItemDonasiDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailDonasi = dataSnapshot.getValue(donasi.class);
                Log.d("namonod",detailDonasi.getJudul());
                Picasso.get().load(detailDonasi.gambar).into(detailFoto);
                detailJudul.setText(detailDonasi.judul);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ButterKnife.bind(this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
