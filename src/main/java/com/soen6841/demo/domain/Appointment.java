package com.soen6841.demo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patient")
    private String patient;
    @Column(name = "patientUserID")
    private String patientUserID;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "doctorUserID")
    private String doctorUserID;
    @Column(name = "nurse")
    private String nurse;
    @Column(name = "nurseUserID")
    private String nurseUserID;
    @Column(name = "reviewer")
    private String reviewer;
    @Column(name = "assignedStatus")
    private Status assignedStatus;
    @Column(name = "appointmentDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    public Appointment() {
    }

    public String getPatientUserID() {
        return patientUserID;
    }

    public void setPatientUserID(String patientUserID) {
        this.patientUserID = patientUserID;
    }

    public String getDoctorUserID() {
        return doctorUserID;
    }

    public void setDoctorUserID(String doctorUserID) {
        this.doctorUserID = doctorUserID;
    }

    public String getNurseUserID() {
        return nurseUserID;
    }

    public void setNurseUserID(String nurseUserID) {
        this.nurseUserID = nurseUserID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Status getAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(Status assignedStatus) {
        this.assignedStatus = assignedStatus;
    }
}
