
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
    private Double token;
    @SerializedName("isStudent")
    @Expose
    private boolean isStudent;
    @SerializedName("classList")
    @Expose
    private List<Class> classList = null;
    @SerializedName("IDCartImage")
    @Expose
    private String imgUrl;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("subjectList")
    @Expose
    private List<String> subjectList = null;

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

    public User(String email, String userName, String password, String firstName, String lastName, String phone, Gender gender, String address, boolean isStudent, String imgUrl, Integer price) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.isStudent = isStudent;
        this.imgUrl = imgUrl;
        this.price = price;
    }
    public User(String email, String userName, String firstName, String lastName, String phone, Gender gender, String address, boolean isStudent, String imgUrl, Integer price) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.isStudent = isStudent;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public User(String email, String userName, String password, String firstName, String lastName, Gender gender, String address, String district, String province, Double token, boolean isStudent, Object createdAt, List<Class> classList, String imgUrl) {
        super();
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.district = district;
        this.province = province;
        this.token = token;
        this.isStudent = isStudent;
        this.classList = classList;
        this.imgUrl = imgUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Gender getGender() {
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

    public Double getToken() {
        return token;
    }

    public void setToken(Double token) {
        this.token = token;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
