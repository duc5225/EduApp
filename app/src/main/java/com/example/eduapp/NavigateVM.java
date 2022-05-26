package com.example.eduapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigateVM extends ViewModel {
    private MutableLiveData<Event<Boolean>> redirect;

    public MutableLiveData<Event<Boolean>> getRedirect() {
        if (redirect == null) {
            redirect = new MutableLiveData<>();
        }
        return redirect;
    }

    public void redirect() {
        this.redirect.setValue(new Event<>(true));
    }
}