package com.abir.roomlivedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abir.roomlivedata.DATABASE.CART_MODEL;
import com.abir.roomlivedata.DATABASE.CART_REPOSITORY;

public class CARTDATABASE_VIEWMODEL extends AndroidViewModel {
    public CART_REPOSITORY mRepository;
    LiveData<CART_MODEL> mAllcarts;

    public CARTDATABASE_VIEWMODEL(@NonNull Application application) {
        super(application);
        mRepository = new CART_REPOSITORY(application);
        mAllcarts = mRepository.getAllCart();
    }

    public LiveData<CART_MODEL> getAllWords() {
        return mAllcarts;
    }

    public void insert(CART_MODEL word) {
        mRepository.insert(word);
    }

    public void Delete(CART_MODEL word) {
        mRepository.delete(word);
    }

    public void DeleteALL(CART_MODEL word) {
        mRepository.deleteall(word);
    }

    public void Deletesingle(CART_MODEL word, String s) {
        mRepository.deletesingle(word, s);
    }

    public CART_MODEL find_cart(CART_MODEL model) {
        return mRepository.find_single(model);
    }

    public void update_cart(CART_MODEL model, String o, String n) {
        mRepository.update_single(model, o, n);
    }

    public void update(CART_MODEL word) {
        mRepository.update(word);
    }
}
