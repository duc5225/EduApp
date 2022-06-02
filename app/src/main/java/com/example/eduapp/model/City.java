/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.eduapp.model;


public class City {

    private int id;
    private String name;
    private String numberOfCourses;
    private int imageResource;

    public City() {
    }

    public City(int id, String name, String numberOfCourses, int imageResource) {
        this.id = id;
        this.name = name;
        this.numberOfCourses = numberOfCourses;
        this.imageResource = imageResource;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfCourses='" + numberOfCourses + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumberOfCourses() {
        return numberOfCourses;
    }

    public int getImageResource() {
        return imageResource;
    }
}
