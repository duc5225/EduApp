package com.example.eduapp;

public class Event<T>{
    private T content;
    private Boolean hasBeenHandled = false;

    Event(T content){
        this.content = content;
    }
    public T getContentIfNotHandled(){
        if (hasBeenHandled) {
           return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent(){
        return content;
    }
}
