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
import android.widget.Toast;

import com.example.kienz.domaku.model.donasi;
import com.example.kienz.domaku.model.mengambil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class explore_detailActivity extends AppCompatActivity {

    @BindView(R.id.explore_detail_back)
    ImageButton backButton;
    @BindView(R.id.explore_detail_photo)
    ImageView detailFoto;
    @BindView(R.id.explore_detail_judul)
    TextView detailJudul;
    @BindView(R.id.explore_detail_sisa)
    TextView detailSisa;
    @BindView(R.id.explore_detail_jumlah)
    TextView detailJumlah;
    @BindView(R.id.explore_detail_alamat)
    TextView detailAlamat;
    @BindView(R.id.explore_detail_ambil)
    Button detailAmbil;
    private donasi detailDonasi;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);
        ButterKnife.bind(this);
        detailAmbil.setEnabled(false);
        final String id = getIntent().getStringExtra("idDonasi");
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference mItemDonasiDatabaseReference = mFirebaseDatabase.getReference().child("Donasi/" + id);
        final DatabaseReference mAmbilDatabaseReference = mFirebaseDatabase.getReference().child("Mengambil/" + mAuth.getUid());

        mItemDonasiDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailDonasi = dataSnapshot.getValue(donasi.class);
                if (detailDonasi.donatur.equals(mAuth.getUid())||detailDonasi.getSisa()<1) {
                    detailAmbil.setEnabled(false);
                }else{
                    Query qSudahAmbil = mAmbilDatabaseReference.orderByChild("donasiId").equalTo(id);
                    qSudahAmbil.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                detailAmbil.setEnabled(false);
                            }else{
                                detailAmbil.setEnabled(true);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        detailAmbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemDonasiDatabaseReference.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                        donasi updatedetailDonasi = mutableData.getValue(donasi.class);
                        if(updatedetailDonasi.getSisa()>=0){
                            updatedetailDonasi.setSisa(updatedetailDonasi.getSisa()-1);
                            mutableData.setValue(updatedetailDonasi);
                            return Transaction.success(mutableData);
                        }else{
                            return Transaction.abort();
                        }
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        final mengambil newAmbil = new mengambil(mAuth.getUid(),dataSnapshot.getKey());
                        if(!detailDonasi.donatur.equals(mAuth.getUid())){
                            mAmbilDatabaseReference.push().setValue(newAmbil).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(explore_detailActivity.this, "Anda berhasil mengambil kupon ini", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        updateUI();
                    }
                });
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void updateUI(){
        Picasso.get().load(detailDonasi.getGambar()).into(detailFoto);
        detailJudul.setText(detailDonasi.getJudul());
        detailSisa.setText(String.valueOf(detailDonasi.getSisa()));
        detailAlamat.setText(detailDonasi.getAlamat());
        detailJumlah.setText(String.valueOf(detailDonasi.getQty()));
    }
}
