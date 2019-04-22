package com.example.rek.fragmentrecyclerview;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class CharacterViewModelFactory implements ViewModelProvider.Factory {

    private Application app;
    private Character c;

    public CharacterViewModelFactory(Application application, Character c) {
        this.app = application;
        this.c = c;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        return null;
        return (T) new CharacterViewModel(app, c);
    }


}
