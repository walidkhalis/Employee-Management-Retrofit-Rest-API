package com.example.retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    @SerializedName("image")
    @Expose
    private String image;
    private String grade;
    private int tel;
    private String cin;
    private String ville;
    private Service service;
    private String emailId;

    public Service getService() {
        return service;
    }

    public String getCin() {
        return cin;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGrade() {
        return grade;
    }

    public int getTel() {
        return tel;
    }

    public String getVille() {
        return ville;
    }

    public String getEmailId() {
        return emailId;
    }
}
