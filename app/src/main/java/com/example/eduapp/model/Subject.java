/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.eduapp.model;

public class Subject {
    private int Id;
    private int imageSubject;
    private String name;
    private String quantitySubject;
    private Boolean isStudent;

    public Subject() {
    }

    public Subject(int id, int imageSubject, String name, String quantitySubject, Boolean isStudent) {
        Id = id;
        this.imageSubject = imageSubject;
        this.name = name;
        this.quantitySubject = quantitySubject;
        this.isStudent = isStudent;
    }

    public Subject(int imageSubject, String name, String quantitySubject) {
        this.imageSubject = imageSubject;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantitySubject() {
        return quantitySubject;
    }

    public void setQuantitySubject(String quantitySubject) {
        this.quantitySubject = quantitySubject;
    }

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    @Override()
    public boolean equals(Object other) {
        if (other instanceof Subject) {
            Subject subject = (Subject) other;
            return subject.getId()==(this.getId());
        }
        return false;
    }
}
