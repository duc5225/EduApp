
package com.example.eduapp.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    private String id;
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
    private String cityId;
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

    private List<Notification> notifications = null;

    private Map<String, Boolean> loveUserList = null;

    private Map<String, Boolean> loveClassList = null;

    private List<Transaction> transactions = null;

    private int reputation;

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

    public User(String email, String userName, String password, String firstName, String lastName, String phone, Gender gender, String address, String cityId, boolean isStudent, String imgUrl, Integer price, Integer reputation, List<String> subjectList) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.cityId = cityId;
        this.isStudent = isStudent;
        this.imgUrl = imgUrl;
        this.price = price;
        this.reputation = reputation;
        this.subjectList = subjectList;
    }
    public User(String email, String userName, String firstName, String lastName, String phone, Gender gender, String address, String cityId, boolean isStudent, String imgUrl, Integer price, List<String> subjectList) {
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.cityId = cityId;
        this.isStudent = isStudent;
        this.imgUrl = imgUrl;
        this.price = price;
        this.subjectList = subjectList;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Map<String, Boolean> getLoveUserList() {
        return loveUserList;
    }

    public void setLoveUserList(Map<String, Boolean> loveUserList) {
        this.loveUserList = loveUserList;
    }

    public Map<String, Boolean> getLoveClassList() {
        return loveClassList;
    }

    public void setLoveClassList(Map<String, Boolean> loveClassList) {
        this.loveClassList = loveClassList;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
