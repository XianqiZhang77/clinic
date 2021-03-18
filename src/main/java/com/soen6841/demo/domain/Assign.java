package com.soen6841.demo.domain;

public class Assign {

    private String doctorUserID;
    private String patientUserID;

    public Assign(String doctorUserID, String patientUserID) {
        this.doctorUserID = doctorUserID;
        this.patientUserID = patientUserID;
    }

    public Assign() {
    }

    public String getDoctorUserID() {
        return doctorUserID;
    }

    public void setDoctorUserID(String doctorUserID) {
        this.doctorUserID = doctorUserID;
    }

    public String getPatientUserID() {
        return patientUserID;
    }

    public void setPatientUserID(String patientUserID) {
        this.patientUserID = patientUserID;
    }
}
