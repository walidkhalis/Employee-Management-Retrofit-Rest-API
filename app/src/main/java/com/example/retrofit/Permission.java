package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permission {
    private int id;

    @SerializedName("motif")
    @Expose
    private String oui;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dateDebut")
    @Expose
    private String dateDebut;
    @SerializedName("dateFin")
    @Expose
    private String dateFin;
    @SerializedName("dateReprise")
    @Expose
    private String dateReprise;
    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("status")
    @Expose
    private String status;

    public Permission(Employee employee,String dateDebut,String dateReprise,String dateFin,String motif,String type,String status) {
        this.employee = employee;
        this.dateDebut = dateDebut;
        this.dateFin = dateReprise;
        this.dateReprise = dateFin;
        this.oui = motif;
        this.type=type;
        this.status=status;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getDateReprise() {
        return dateReprise;
    }


    public Employee getEmployee() {
        return employee;
    }
}
