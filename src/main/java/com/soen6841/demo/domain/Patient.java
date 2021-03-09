package com.soen6841.demo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userID")
    private String userID;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "registerStatus")
    private Status registerStatus;   
    @Column(name = "reviewer")
    private String reviewer;
    @Column(name = "appointmentStatus")
    private Status appointmentStatus;
    @Column(name = "assignee")
    private String assignee; 
    @Column(name = "answerOne")
    private String answerOne;
    @Column(name = "answerTwo")
    private String answerTwo;
    @Column(name = "answerThree")
    private String answerThree;
    @Column(name = "answerFour")
    private String answerFour;

    public Patient(String userID, String fullName, String email, String address, String phoneNumber, Date birthDate, Status registerStatus, Status appointmentStatus) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.registerStatus = registerStatus;
        this.appointmentStatus = appointmentStatus;
    }
    
    public Patient(String userID, String fullName, String email, String address, String phoneNumber, Date birthDate, Status registerStatus, Status appointmentStatus, String reviewer, String assignee, String answerOne, String answerTwo, String answerThree, String answerFour) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.registerStatus = registerStatus;
        this.appointmentStatus = appointmentStatus;
        this.reviewer = reviewer;
        this.assignee = assignee;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Status getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Status registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    
    public Status getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Status appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
    
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAnswerOne() { return answerOne; }

    public void setAnswerOne(String answerOne) { this.answerOne = answerOne; }

    public String getAnswerTwo() { return answerTwo; }

    public void setAnswerTwo(String answerTwo) { this.answerTwo = answerTwo; }

    public String getAnswerThree() { return answerThree; }

    public void setAnswerThree(String answerThree) { this.answerThree = answerThree; }

    public String getAnswerFour() { return answerFour; }

    public void setAnswerFour(String answerFour) { this.answerFour = answerFour; }

    public Patient() {
    }
}
