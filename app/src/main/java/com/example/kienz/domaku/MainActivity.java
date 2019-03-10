package com.example.kienz.domaku;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kienz.domaku.explore.explore_frag;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, explore_frag.OnFragmentInteractionListener, ambil_frag.OnFragmentInteractionListener, donasi_frag.OnFragmentInteractionListener   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kita set default nya Home Fragment
//        loadFragment(new explore_frag());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        // inisialisasi BottomNavigaionView
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
//        // beri listener pada saat item/menu bottomnavigation terpilih
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    // method untuk load fragment yang sesuai
//    private boolean loadFragment(Fragment fragment) {
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fl_container, fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
