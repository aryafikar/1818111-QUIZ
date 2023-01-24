package com.abir.roomlivedata;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public MutableLiveData<String> cart_number = new MutableLiveData<String>();

    public CartViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("8");
    }

    public MutableLiveData<String> getText() {
        if (mText == null) {
            mText = new MutableLiveData<String>();
        }
        return mText;
    }

    public MutableLiveData<String> getTextDynamic() {
        return mText;
    }

    public void setDatacart(String s) {
        Log.d("Change", "Set Data");
        cart_number.setValue(s);
    }
}