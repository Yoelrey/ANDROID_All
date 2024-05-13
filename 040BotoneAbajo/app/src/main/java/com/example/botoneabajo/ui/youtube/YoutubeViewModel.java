package com.example.botoneabajo.ui.youtube;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YoutubeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public YoutubeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is youtube fragment");
    }

    public LiveData<String> getText() {
        return mText;

    }
}
