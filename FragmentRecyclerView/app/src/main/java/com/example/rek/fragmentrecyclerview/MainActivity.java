package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements RecyclerAdapter.ImageClickListener {

    private FragmentManager mFragmentManager;
    private CharacterViewModel mCharacterViewModel;
    private boolean portraitMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        mFragmentManager = getSupportFragmentManager();
        portraitMode = getResources().getBoolean(R.bool.portraitMode);

        createFragments();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_characters);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflato = getMenuInflater();
        inflato.inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuNew:
                transactFragment(new AddCharacterFragment(), true);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void createFragments() {
        // Clear the backstack on configuration change
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (portraitMode) {
            transactFragment(new RecyclerFragment(), false);
        } else {
            transactFragment(new WelcomeFragment(), false);
        }
    }

    private void transactFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transacto = mFragmentManager.beginTransaction();
        transacto.add(R.id.fragContainer, fragment);
        if (addToBackStack) {
            transacto.addToBackStack(null);
        }
        transacto.commit();
    }

    /**
     * Respond to clicks on welcome ImageView of each list_item
     *
     * @param c The character from the list_item
     */
    @Override
    public void respondWelcomeImageclick(Character c) {
        mCharacterViewModel.setCharacter(c);
        transactFragment(new WelcomeFragment(), true);
    }

    /**
     * Respond to clicks on edit ImageView of each list_item
     *
     * @param c The character from the list_item
     */
    @Override
    public void respondEditImageclick(Character c) {
        mCharacterViewModel.setCharacter(c);
        transactFragment(new EditCharacterFragment(), true);
    }

}
