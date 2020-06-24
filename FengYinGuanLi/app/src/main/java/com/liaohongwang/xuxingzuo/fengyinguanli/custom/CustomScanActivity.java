package com.liaohongwang.xuxingzuo.fengyinguanli.custom;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;


public class CustomScanActivity extends AppCompatActivity {

    private CustomScanFragment mScanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scan);

        loadFragment();
    }

    private void loadFragment() {

        mScanFragment = (CustomScanFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mScanFragment == null) {
            mScanFragment = new CustomScanFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.contentFrame, mScanFragment);
            fragmentTransaction.show(mScanFragment);
            fragmentTransaction.commit();
        }
    }
}
