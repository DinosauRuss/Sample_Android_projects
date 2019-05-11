package com.example.rek.customview;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rek.customview.fragments.ButtonFragment;
import com.example.rek.customview.fragments.CounterFragment;
import com.example.rek.customview.fragments.DonutFragment;
import com.example.rek.customview.fragments.OnFragmentInteractionListener;
import com.example.rek.customview.fragments.SelectorFragment;
import com.example.rek.customview.fragments.AvdFragment;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only load selector fragment on first run
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new SelectorFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment frago;
        switch (item.getItemId()) {
            case R.id.menuCounter:
                frago = new CounterFragment();
                break;
            case R.id.menuDonut:
                frago = new DonutFragment();
                break;
            case R.id.menuButtons:
                frago = new ButtonFragment();
                break;
            case R.id.menuAvd:
                frago = new AvdFragment();
                break;
            default:
                return true;
        }
        transactFragment(frago);
        return true;
    }

    @Override
    public void onFragmentInteraction(int id) {

    }

    private void transactFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, frag)
                .addToBackStack(null)
                .commit();
    }

}
