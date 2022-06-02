package com.example.eduapp.model;

import java.util.HashMap;
import java.util.Map;

public class NotiExParam {
  Integer amount;
  String classId;
  String className;
  String imgUrl;

  public NotiExParam(){
  }

  public NotiExParam(Integer amount, String classId, String className, String imgUrl) {
    this.amount = amount;
    this.classId = classId;
    this.className = className;
    this.imgUrl = imgUrl;
  }

  public NotiExParam(Integer amount){
    this.amount = amount;
    classId = "";
    className = "";
    imgUrl = "";
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<>();
    result.put("classId", classId);
    result.put("amount", amount);
    result.put("className", className);
    result.put("imgUrl", imgUrl);

    return result;
  }
}
