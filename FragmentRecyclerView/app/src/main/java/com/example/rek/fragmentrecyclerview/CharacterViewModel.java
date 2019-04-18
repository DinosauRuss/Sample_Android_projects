package com.example.rek.fragmentrecyclerview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private String mName;
    private LiveData<List<Character>> mData;
    private CharacterRepository mRepo;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        mRepo = new CharacterRepository(application);
        mData = mRepo.getAllCharacters();
    }

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
        return mData;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

}
