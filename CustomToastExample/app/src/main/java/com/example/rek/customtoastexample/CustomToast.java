package com.example.rek.customtoastexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static Toast makeGreenToast(Activity activity, String text, int length) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        ViewGroup container = activity.findViewById(R.id.toast_container_green);
        View v = inflater.inflate(R.layout.toast_green, container, false);
        TextView texto = v.findViewById(R.id.tvToast);
        texto.setText(text);

        Toast t = new Toast(activity);
        t.setDuration(length);
        t.setView(v);

        return t;
    }

}
