package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private CharacterViewModel mCharacterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);

        // Only add fragment first time onCreate runs
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frag_container, new RecyclerFragment())
                    .commit();
        }
    }

}
