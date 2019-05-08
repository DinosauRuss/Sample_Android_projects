package com.example.rek.customview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.rek.customview.fragments.ButtonFragment;
import com.example.rek.customview.fragments.CounterFragment;
import com.example.rek.customview.fragments.DonutFragment;
import com.example.rek.customview.fragments.OnFragmentInteractionListener;
import com.example.rek.customview.fragments.SelectorFragment;

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
    public void onFragmentInteraction(View v) {
        Fragment frago;
        switch (v.getId()) {
            case R.id.btnCounter:
                frago = new CounterFragment();
                break;
            case R.id.btnDonut:
                frago = new DonutFragment();
                break;
            case R.id.btnButton:
                frago = new ButtonFragment();
                break;
            default:
                return;
        }
        transactFragment(frago);

    }

    private void transactFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, frag)
                .addToBackStack(null)
                .commit();
    }

}
