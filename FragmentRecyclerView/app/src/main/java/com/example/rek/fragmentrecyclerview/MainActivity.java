package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ImageClickListener{

    private SharedViewModel mSharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        // Only create fragment first time onCreate runs
        if (savedInstanceState == null ) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frag_container, new RecyclerFragment())
                    .commit();
        }
    }

    /**
     * Respond to clicks on the ImageView of each list_item
     * @param name The name text from list_item TextView
     */
    @Override
    public void respondImageclick(String name) {

        mSharedViewModel.setName(name);

        // Create WelcomeFragment displaying name
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, new WelcomeFragment())
                .addToBackStack(null)
                .commit();
    }

}
