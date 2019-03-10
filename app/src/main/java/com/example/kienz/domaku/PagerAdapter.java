package com.example.kienz.domaku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kienz.domaku.explore.explore_frag;

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
        }
        return null;
    }

    @Override

    public int getCount() {
        return 3;
    }

    @Override

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Eksplor";
            case 1: return "Donasi";
            case 2: return "Ambil";
            default: return null;
        }
    }
}
