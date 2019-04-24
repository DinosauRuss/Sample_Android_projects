package com.example.rek.fragmentrecyclerviewpaging;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class CharacterViewModelFactory implements ViewModelProvider.Factory {

    private Application app;
    private Character character;

    public CharacterViewModelFactory(Application application, Character character) {
        this.app = application;
        this.character = character;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        return null;
        return (T) new CharacterViewModel(app, character);
    }


}
