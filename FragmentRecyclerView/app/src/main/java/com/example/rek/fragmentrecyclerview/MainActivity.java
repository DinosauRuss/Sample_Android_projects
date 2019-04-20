package com.example.rek.fragmentrecyclerview;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT_TAG = "recycler_fragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
         RecyclerFragment mRecyclerFragment = (RecyclerFragment) fm.findFragmentByTag(FRAGMENT_TAG);

        // If fragment is not null, it is being retained across configuration change
        if (mRecyclerFragment == null) {
            fm.beginTransaction()
                    .add(R.id.frag_container, new RecyclerFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }

}
