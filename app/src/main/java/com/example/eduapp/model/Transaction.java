package com.example.eduapp.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Transaction {
  String id = UUID.randomUUID().toString();
  String title;
  Integer amount;
  Boolean isTopup;
  String paymentMethod = "Momo";

  public Transaction(){

  }
  public Transaction(String title, Integer amount, Boolean isTopup) {
    this.title = title;
    this.amount = amount;
    this.isTopup = isTopup;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Boolean getTopup() {
    return isTopup;
  }

  public void setIsTopup(Boolean isTopup) {
    this.isTopup = isTopup;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<>();
    result.put("title", title);
    result.put("amount", amount);
    result.put("phone", isTopup);
    result.put("timestamp", ServerValue.TIMESTAMP);

    return result;
  }
}
