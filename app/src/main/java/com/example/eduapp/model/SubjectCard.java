/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.eduapp.model;

public class SubjectCard {

    private int Id;
    private int imageSubject;
    private String subjectTitle;
    private String quantitySubject;
    private String urlSubject;

    public SubjectCard(int id, int imageSubject, String subjectTitle, String quantitySubject) {
        Id = id;
        this.imageSubject = imageSubject;
        this.subjectTitle = subjectTitle;
        this.quantitySubject = quantitySubject;
    }

    public SubjectCard(int imageSubject, String subjectTitle, String quantitySubject) {
        this.imageSubject = imageSubject;
        this.subjectTitle = subjectTitle;
        this.quantitySubject = quantitySubject;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImageSubject() {
        return imageSubject;
    }

    public void setImageSubject(int imageSubject) {
        this.imageSubject = imageSubject;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getQuantitySubject() {
        return quantitySubject;
    }

    public void setQuantitySubject(String quantitySubject) {
        this.quantitySubject = quantitySubject;
    }

    public String getUrlSubject() {
        return urlSubject;
    }

    public void setUrlSubject(String urlSubject) {
        this.urlSubject = urlSubject;
    }

    @Override()
    public boolean equals(Object other) {
        if (other instanceof SubjectCard) {
            SubjectCard subjectCard = (SubjectCard) other;
            return subjectCard.getId()==(this.getId());
        }
        return false;
    }
}
