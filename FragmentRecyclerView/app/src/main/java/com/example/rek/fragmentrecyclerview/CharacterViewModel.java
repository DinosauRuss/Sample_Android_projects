package com.example.rek.fragmentrecyclerview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private Character mCharacter;
    private CharacterRepository mRepo;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        mRepo = new CharacterRepository(application);
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

    public LiveData<List<Character>> getAllCharacters() {
        return mRepo.getAllCharacters();
    }


    // Functions for fragment to fragment communication
    public void setCharacter(Character c) {
        this.mCharacter = c;
    }

    public Character getCharacter() {
        return mCharacter;
    }
}
