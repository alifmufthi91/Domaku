package com.example.kienz.domaku.explore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kienz.domaku.R;
import com.example.kienz.domaku.donasi;
import com.example.kienz.domaku.kategori;

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
        listTerdekat = new ArrayList<>();
        listTerbaru = new ArrayList<>();
        donasi a = new donasi("Sisa wedding ada 40 porsi",40,"http://cdn2.tstatic.net/jabar/foto/bank/images/rijsttafel_20180919_094737.jpg",null,null);
        donasi b = new donasi("Abis Syukuran masih ada rendang, sate,  buat 20",20,"https://sejasa-production.s3.amazonaws.com/uploads/attachment/file/554953/Screenshot_2016-06-05-16-04-47_com.facebook.katana.png",null,null);
        listTerdekat.add(a);
        listTerdekat.add(b);
        listTerdekat.add(a);
        listTerdekat.add(b);
        listTerdekat.add(a);
        listTerdekat.add(b);
        listTerbaru.add(b);
        listTerbaru.add(a);
        listTerbaru.add(b);
        listTerbaru.add(a);
        listTerbaru.add(b);
        listTerbaru.add(a);
        kategori eventTerdekat = new kategori("Terdekat",listTerdekat);
        kategori eventTerbaru = new kategori("Terbaru",listTerbaru);
        mKategori.add(eventTerdekat);
        mKategori.add(eventTerbaru);

        adapter = new explore_kategori_adapter(getActivity(),mKategori);
        for (kategori h : mKategori) {
            Log.d("namonn",h.getTitle());
            Log.d("namonno", String.valueOf(adapter.getItemCount()));
        }
        recy.setAdapter(adapter);
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
