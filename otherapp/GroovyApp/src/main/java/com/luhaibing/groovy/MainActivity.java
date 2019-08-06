package com.luhaibing.groovy;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint("CommitTransaction") FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl, new BlankFragment());
        transaction.commitAllowingStateLoss();
    }
}
