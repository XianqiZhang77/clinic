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
    @Column(name = "healthCareName")
    private String healthCareName;
    @Column(name = "healthCareID")
    private String healthCareID;  
    @Column(name = "note")
    private String note;
    @Column(name = "appointmentDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Column(name = "appointmentStatus")
    private Status appointmentStatus; // cancelled, available

    public Appointment(String patient, String patientUserID, String healthCareName, String healthCareID, String note, Date appointmentDate, Status appointmentStatus) {
    	this.patient = patient;
    	this.patientUserID = patientUserID;
    	this.healthCareName = healthCareName;
    	this.healthCareID = healthCareID;
    	this.note = note;
    	this.appointmentDate = appointmentDate;
    	this.appointmentStatus = appointmentStatus;
    }
    
    public Appointment() {
    }

    public Status getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Status appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getPatientUserID() {
        return patientUserID;
    }

    public void setPatientUserID(String patientUserID) {
        this.patientUserID = patientUserID;
    }

    public String getHealthCareID() {
        return healthCareID;
    }

    public void setHealthCareID(String healthCareID) {
        this.healthCareID = healthCareID;
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

    public String getHealthCareName() {
        return healthCareName;
    }

    public void setHealthCareName(String healthCareName) {
        this.healthCareName = healthCareName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

