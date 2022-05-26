package com.example.eduapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TutorProfile {

  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("age")
  @Expose
  private Integer age;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("rate")
  @Expose
  private Integer rate;
  @SerializedName("subjectList")
  @Expose
  private List<String> subjectList = null;

  /**
   * No args constructor for use in serialization
   */
  public TutorProfile() {
  }

  /**
   * @param subjectList
   * @param rate
   * @param name
   * @param description
   * @param age
   */
  public TutorProfile(String name, Integer age, String description, Integer rate, List<String> subjectList) {
    super();
    this.name = name;
    this.age = age;
    this.description = description;
    this.rate = rate;
    this.subjectList = subjectList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public List<String> getSubjectList() {
    return subjectList;
  }

  public void setSubjectList(List<String> subjectList) {
    this.subjectList = subjectList;
  }

}