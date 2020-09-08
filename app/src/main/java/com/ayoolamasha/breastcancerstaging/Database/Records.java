package com.ayoolamasha.breastcancerstaging.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "patients_records")
public class Records {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String patientName;

    private int patientAge;

    private String stagingDetails;

    private int stageFigure;

    private String dateAdded;

    public Records() {
    }

    public Records(String patientName, int patientAge, String stagingDetails, int stageFigure) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.stagingDetails = stagingDetails;
        this.stageFigure = stageFigure;
//        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getStagingDetails() {
        return stagingDetails;
    }

    public void setStagingDetails(String stagingDetails) {
        this.stagingDetails = stagingDetails;
    }

    public int getStageFigure() {
        return stageFigure;
    }

    public void setStageFigure(int stageFigure) {
        this.stageFigure = stageFigure;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
