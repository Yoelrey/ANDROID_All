package com.example.a54fragmentsexamen1a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    boolean mDualPane;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MasterFragment masterFragment = null;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) FrameLayout frameLayout = findViewById(R.id.frameLayout);
        if (frameLayout != null) {
            mDualPane = false;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentByTag("MASTER");
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }
            DetailFragment detailFragment = (DetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }
            fragmentTransaction.commit();
        } else {
            mDualPane = true;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutMaster);
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }
            fragmentTransaction.commit();
        }
        masterFragment.setOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListener() {
            @Override
            public void onItemSelected(String countryName) {
                sendCountryName(countryName);
            }
        });
    }

    private void sendCountryName(String countryName) {
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frameLayoutDetail);
        if (detailFragment != null) {
            detailFragment.showSelectedCountry(countryName);
        }
    }


}
