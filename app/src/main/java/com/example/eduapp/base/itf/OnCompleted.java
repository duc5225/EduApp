package com.example.eduapp.base.itf;

public interface OnCompleted<T> {
  void onFinish(T object);
  default void onError(String error){};
}