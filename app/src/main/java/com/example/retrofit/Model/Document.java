package com.example.retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    private int id;

    @SerializedName("motif")
    @Expose
    private String oui;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("status")
    @Expose
    private String status;

    public Document(Employee employee,String motif,String type,String status) {
        this.employee = employee;
        this.oui = motif;
        this.type=type;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setMotif(String motif) {
        this.oui = motif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMotif() {
        return oui;
    }

    public String getType() {
        return type;
    }


    public Employee getEmployee() {
        return employee;
    }
}
