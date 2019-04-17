package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.ViewModel;


public class SharedViewModel extends ViewModel {

    private String mName;


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

}
