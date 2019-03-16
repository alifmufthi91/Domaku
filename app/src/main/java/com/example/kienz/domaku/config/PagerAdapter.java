package com.example.kienz.domaku.config;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kienz.domaku.ambil.ambil_frag;
import com.example.kienz.domaku.donasi.donasi_frag;
import com.example.kienz.domaku.explore.explore_frag;
import com.example.kienz.domaku.profile.profile_frag;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new explore_frag();
            case 1: return new donasi_frag();
            case 2: return new ambil_frag();
            case 3: return new profile_frag();
        }
        return null;
    }

    @Override

    public int getCount() {
        return 4;
    }

    @Override

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Eksplor";
            case 1: return "Donasi";
            case 2: return "Ambil";
            case 3: return "Profil";
            default: return null;
        }
    }
}
