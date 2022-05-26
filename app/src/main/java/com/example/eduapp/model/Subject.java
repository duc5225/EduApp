package com.example.eduapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Subject {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("code")
  @Expose
  private String code;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("classList")
  @Expose
  private List<Object> classList = null;

  /**
   * No args constructor for use in serialization
   */
  public Subject() {
  }

  /**
   * @param code
   * @param name
   * @param id
   * @param classList
   */
  public Subject(Integer id, String code, String name, List<Object> classList) {
    super();
    this.id = id;
    this.code = code;
    this.name = name;
    this.classList = classList;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Object> getClassList() {
    return classList;
  }

  public void setClassList(List<Object> classList) {
    this.classList = classList;
  }

}