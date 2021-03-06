package com.example.kienz.domaku;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kienz.domaku.ambil.ambil_frag;
import com.example.kienz.domaku.donasi.donasi_frag;
import com.example.kienz.domaku.explore.explore_frag;
import com.example.kienz.domaku.config.PagerAdapter;
import com.example.kienz.domaku.profile.profile_frag;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, explore_frag.OnFragmentInteractionListener, ambil_frag.OnFragmentInteractionListener, donasi_frag.OnFragmentInteractionListener, profile_frag.OnFragmentInteractionListener  {

    TabLayout tabLayout;
    public FirebaseDatabase mFirebaseDatabase;
    public DatabaseReference mDonasiDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kita set default nya Home Fragment
//        loadFragment(new explore_frag());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDonasiDatabaseReference = mFirebaseDatabase.getReference().child("Donasi");

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


    private void createTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText( tabLayout.getTabAt(0).getText());
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_explore_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(tabLayout.getTabAt(1).getText());
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_donasi_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(tabLayout.getTabAt(2).getText());
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ambil_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText(tabLayout.getTabAt(3).getText());
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_person_black_24dp, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
