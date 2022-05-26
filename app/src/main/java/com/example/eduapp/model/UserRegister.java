package com.example.eduapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegister {
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("username")
  @Expose
  private String username;
  @SerializedName("password")
  @Expose
  private String password;
  @SerializedName("firstName")
  @Expose
  private String firstName;
  @SerializedName("lastName")
  @Expose
  private String lastName;
  @SerializedName("DoB")
  @Expose
  private String doB;
  @SerializedName("gender")
  @Expose
  private String gender;
  @SerializedName("address")
  @Expose
  private String address;
  @SerializedName("isStudent")
  @Expose
  private String isStudent;
  @SerializedName("IDCartImage")
  @Expose
  private String iDCartImage;

  /**
   * No args constructor for use in serialization
   *
   */
  public UserRegister() {
  }

  /**
   *
   * @param firstName
   * @param lastName
   * @param password
   * @param address
   * @param gender
   * @param doB
   * @param iDCartImage
   * @param email
   * @param isStudent
   * @param username
   */
  public UserRegister(String email, String username, String password, String firstName, String lastName, String doB, String gender, String address, String isStudent, String iDCartImage) {
    super();
    this.email = email;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.doB = doB;
    this.gender = gender;
    this.address = address;
    this.isStudent = isStudent;
    this.iDCartImage = iDCartImage;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDoB() {
    return doB;
  }

  public void setDoB(String doB) {
    this.doB = doB;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getIsStudent() {
    return isStudent;
  }

  public void setIsStudent(String isStudent) {
    this.isStudent = isStudent;
  }

  public String getIDCartImage() {
    return iDCartImage;
  }

  public void setIDCartImage(String iDCartImage) {
    this.iDCartImage = iDCartImage;
  }

}