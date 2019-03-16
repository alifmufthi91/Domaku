package com.example.kienz.domaku.explore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.domaku.MainActivity;
import com.example.kienz.domaku.R;
import com.example.kienz.domaku.model.donasi;
import com.example.kienz.domaku.model.kategori;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link explore_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link explore_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class explore_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public explore_kategori_adapter adapter;
    ArrayList<kategori> mKategori;
    ArrayList<donasi> listTerdekat;
    ArrayList<donasi> listTerbaru;
    @BindView(R.id.recyclerView_explore_list)
    RecyclerView recy;
    private MainActivity Activity;
//
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mDonasiDatabaseReference;

    private OnFragmentInteractionListener mListener;

    public explore_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment explore_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static explore_frag newInstance(String param1, String param2) {
        explore_frag fragment = new explore_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mDonasiDatabaseReference = mFirebaseDatabase.getReference().child("Donasi");
        Activity = (MainActivity) getActivity();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);

        ButterKnife.bind(this,v);
        mKategori = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(layoutManager);
        recy.setNestedScrollingEnabled(false);
        adapter = new explore_kategori_adapter(getActivity(),mKategori);

        recy.setAdapter(adapter);
        listTerdekat = new ArrayList<>();
        listTerbaru = new ArrayList<>();
        kategori eventTerdekat = new kategori("Terdekat",listTerdekat);
        kategori eventTerbaru = new kategori("Terbaru",listTerbaru);

        Activity.mDonasiDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                donasi don = dataSnapshot.getValue(donasi.class);
                don.setId(dataSnapshot.getKey());
                Log.d("namonnno",don.getJudul());
                listTerbaru.add(0,don);
                if(listTerbaru.size() > 6)
                    listTerbaru.remove(listTerbaru.size() - 1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mKategori.add(eventTerdekat);
        mKategori.add(eventTerbaru);
        for (kategori h : mKategori) {
            Log.d("namonn",h.getTitle());
            Log.d("namonno", String.valueOf(adapter.getItemCount()));
        }

        adapter.notifyDataSetChanged();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
