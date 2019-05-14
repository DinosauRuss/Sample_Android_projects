package com.example.rek.customview.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.rek.customview.R;
import com.example.rek.customview.Utils;
import com.example.rek.customview.custom_views.DigitScroller;
import com.example.rek.customview.custom_views.ScrollCounter;


public class ScrollCounterFragment extends Fragment
        implements EditText.OnKeyListener, View.OnClickListener{

    DigitScroller singleScroller;
    EditText edtSingleScroller;

    ScrollCounter counter;
    EditText edtCounter;

    public ScrollCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scroll_counter, container, false);

        singleScroller = v.findViewById(R.id.digitScroller);

        final Button btnIncrement = v.findViewById(R.id.btnIncrement);
        btnIncrement.setOnClickListener(this);

        edtSingleScroller = v.findViewById(R.id.edtDigit);
        edtSingleScroller.setOnKeyListener(this);

        counter = v.findViewById(R.id.counter);
        counter.setCount(1988);

        final Button btnIncrementScrollCounter = v.findViewById(R.id.btnIncrementScrollCounter);
        btnIncrementScrollCounter.setOnClickListener(this);

        edtCounter = v.findViewById(R.id.edtCounter);
        edtCounter.setOnKeyListener(this);

        return v;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        Log.d(Utils.TAG, String.valueOf(singleScroller.getWidth()));
        Log.d(Utils.TAG, String.valueOf(singleScroller.getHeight()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIncrement:
                singleScroller.increment();
                break;
            case R.id.btnIncrementScrollCounter:
                counter.increment();
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ( (event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER) ) {

            switch (v.getId()){
                case R.id.edtDigit:
                    setSingleScrollerDigit(v);
                    return true;
                case R.id.edtCounter:
                    setCounterNumber(v);
                    return true;
            }
        }
        return false;
    }

    private void setSingleScrollerDigit(View v) {
        String getDigit = edtSingleScroller.getText().toString();
        if (!getDigit.equals("")) {
            int newDigit = Integer.parseInt(getDigit);
            singleScroller.setDigitAnim(newDigit);
            edtSingleScroller.setText("");
        }
        hideKeyboardFrom(v.getContext(), edtSingleScroller);
    }

    private void setCounterNumber(View v) {
        String getNumber = edtCounter.getText().toString();
        if (!getNumber.equals("")) {
            int newDigit = Integer.parseInt(getNumber);
            counter.setAnimCount(newDigit);
            edtCounter.setText("");
        }
        hideKeyboardFrom(v.getContext(), edtCounter);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
