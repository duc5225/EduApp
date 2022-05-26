
package com.example.eduapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subjectName")
    @Expose
    private Object subjectName;
    @SerializedName("tutorId")
    @Expose
    private Integer tutorId;
    @SerializedName("tutorName")
    @Expose
    private Object tutorName;
    @SerializedName("fromDate")
    @Expose
    private Object fromDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Class() {
    }

    /**
     * 
     * @param fromDate
     * @param tutorId
     * @param tutorName
     * @param endDate
     * @param id
     * @param subjectName
     */
    public Class(Integer id, Object subjectName, Integer tutorId, Object tutorName, Object fromDate, Object endDate) {
        super();
        this.id = id;
        this.subjectName = subjectName;
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.fromDate = fromDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(Object subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Object getTutorName() {
        return tutorName;
    }

    public void setTutorName(Object tutorName) {
        this.tutorName = tutorName;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

}
