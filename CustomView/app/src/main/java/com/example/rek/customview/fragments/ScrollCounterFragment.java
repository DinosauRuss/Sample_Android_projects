package com.example.rek.customview.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rek.customview.R;
import com.example.rek.customview.custom_views.DigitScroller;


public class ScrollCounterFragment extends Fragment {

    private static final int MAX_DIGIT = 9;
    private static final int MIN_DIGIT = 0;

    DigitScroller dig;
    EditText edito;

    public ScrollCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scroll_counter, container, false);

        dig = v.findViewById(R.id.digitScroller);

        final Button btnIncrement = v.findViewById(R.id.btnIncrement);
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.increment();
            }
        });

        edito = v.findViewById(R.id.edtDigit);
        edito.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ( (event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) ) {

                    setDigScrollerDigit(v);
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    private void setDigScrollerDigit(View v) {
        int newDigit = constrainDigit( Integer.parseInt(edito.getText().toString()) );
        dig.setDigit( newDigit );
        hideKeyboardFrom(v.getContext(), edito);
    }

    private int constrainDigit(int digit) {
        return Math.max( Math.min(digit, MAX_DIGIT), MIN_DIGIT );
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
