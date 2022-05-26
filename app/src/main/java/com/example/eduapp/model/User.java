
package com.example.eduapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("dob")
    @Expose
    private Object dob;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("bpoint")
    @Expose
    private Double bpoint;
    @SerializedName("isStudent")
    @Expose
    private Integer isStudent;
    @SerializedName("createdAt")
    @Expose
    private Object createdAt;
    @SerializedName("classList")
    @Expose
    private List<Class> classList = null;
    @SerializedName("IDCartImage")
    @Expose
    private String iDCartImage;

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public enum Gender {
        male, female, other
    }
    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    public User(String email, String userName, String password, String firstName, String lastName, String phone, Object dob, Gender gender, String address, Integer isStudent, String iDCartImage) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.isStudent = isStudent;
        this.iDCartImage = iDCartImage;
    }

    /**
     * 
     * @param lastName
     * @param address
     * @param gender
     * @param userName
     * @param firstName
     * @param createdAt
     * @param province
     * @param dob
     * @param district
     * @param bpoint
     * @param email
     * @param isStudent
     * @param classList
     */
    public User(String email, String userName, String password, String firstName, String lastName, Object dob, Gender gender, String address, String district, String province, Double bpoint, Integer isStudent, Object createdAt, List<Class> classList, String iDCartImage) {
        super();
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.district = district;
        this.province = province;
        this.bpoint = bpoint;
        this.isStudent = isStudent;
        this.createdAt = createdAt;
        this.classList = classList;
        this.iDCartImage = iDCartImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getiDCartImage() {
        return iDCartImage;
    }

    public void setiDCartImage(String iDCartImage) {
        this.iDCartImage = iDCartImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getBpoint() {
        return bpoint;
    }

    public void setBpoint(Double bpoint) {
        this.bpoint = bpoint;
    }

    public Integer getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Integer isStudent) {
        this.isStudent = isStudent;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

}
