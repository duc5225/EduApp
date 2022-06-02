
package com.example.eduapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subjectId")
    @Expose
    private String userId;

    private String name;

    private String subjectId;

    private String topic;

    private int lengthInMinute;

    private String address;

    private String phone;

    private String cityId;

    private Integer price;

    private boolean isOnline;

    private String description;

    private String time;

    private String isTaken;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Class() {
    }

    public Class(String id, String userId, String name, String subjectId, String topic, int lengthInMinute, String address, String phone, String cityId, Integer price, boolean isOnline, String description, String time, String isTaken) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.subjectId = subjectId;
        this.topic = topic;
        this.lengthInMinute = lengthInMinute;
        this.address = address;
        this.phone = phone;
        this.cityId = cityId;
        this.price = price;
        this.isOnline = isOnline;
        this.description = description;
        this.time = time;
        this.isTaken = isTaken;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getLengthInMinute() {
        return lengthInMinute;
    }

    public void setLengthInMinute(int lengthInMinute) {
        this.lengthInMinute = lengthInMinute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTaken() {
        return isTaken;
    }

    public void setTaken(String taken) {
        isTaken = taken;
    }
}
