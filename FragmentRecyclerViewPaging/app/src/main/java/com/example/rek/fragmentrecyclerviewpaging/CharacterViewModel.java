package com.example.rek.fragmentrecyclerviewpaging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private MutableLiveData<Character> mCharacter = new MutableLiveData<>();
    private CharacterRepository mRepo;

    public CharacterViewModel(@NonNull Application application, Character c) {
        super(application);

        mRepo = new CharacterRepository(application);
        setCharacter(c);
    }


    // Functions to access Room Database
    public void insertCharacter(Character character) {
        mRepo.insertCharacter(character);
    }

    public void deleteCharacter(Character character) {
        mRepo.deleteCharacter(character);
    }

    public void updateCharacter(Character character) {
        mRepo.updateCharacter(character);
    }

    public LiveData<PagedList<Character>> getAllCharacters() {
        return mRepo.getAllCharacters();
    }


    // Functions for fragment to fragment communication
    public void setCharacter(Character c) {
        this.mCharacter.setValue(c);
        Log.d("something", "setting character");
    }

    public LiveData<Character> getCharacter() {
        return mCharacter;
    }
}
