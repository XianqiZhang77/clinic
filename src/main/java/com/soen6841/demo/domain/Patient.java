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
    @Column(name = "register_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date registerTime;
    @Column(name = "answerOne")
    private String answerOne;
    @Column(name = "answerTwo")
    private String answerTwo;
    @Column(name = "answerThree")
    private String answerThree;
    @Column(name = "answerFour")
    private String answerFour;
    @Column(name = "answerFive")
    private String answerFive;
    @Column(name = "answerSix")
    private String answerSix;
    @Column(name = "answerSeven")
    private String answerSeven;
    @Column(name = "selfAssessmentTime")
    private Date selfAssessmentTime;
    @Column(name = "reviewStatus")
    private Status reviewStatus;
    @Column(name = "reviewer")
    private String reviewer;
    @Column(name = "assignee")
    private String assignee;

    public Patient(String userID, String fullName, String email, String address, String phoneNumber, Date birthDate, Status registerStatus, Date registerTime, String answerOne, String answerTwo, String answerThree, String answerFour, Date selfAssessmentTime, Status reviewStatus, String reviewer, String assignee) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.registerStatus = registerStatus;
        this.registerTime = registerTime;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.selfAssessmentTime = selfAssessmentTime;
        this.reviewStatus = reviewStatus;
        this.reviewer = reviewer;
        this.assignee = assignee;
    }

    public Patient(String userID, String fullName, String email, String address, String phoneNumber, Date birthDate, Status registerStatus, Date registerTime, String answerOne, String answerTwo, String answerThree, String answerFour, Date selfAssessmentTime, Status reviewStatus) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.registerStatus = registerStatus;
        this.registerTime = registerTime;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.selfAssessmentTime = selfAssessmentTime;
        this.reviewStatus = reviewStatus;
    }

    public Patient(String userID, String fullName, String email, String address, String phoneNumber, Date birthDate, Status registerStatus, Date registerTime, Status reviewStatus) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.registerStatus = registerStatus;
        this.registerTime = registerTime;
        this.reviewStatus = reviewStatus;
    }

    public Long getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public Patient setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Patient setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Patient setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Patient setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Patient setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Patient setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Status getRegisterStatus() {
        return registerStatus;
    }

    public Patient setRegisterStatus(Status registerStatus) {
        this.registerStatus = registerStatus;
        return this;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public Patient setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public Patient setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
        return this;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public Patient setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
        return this;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public Patient setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
        return this;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public Patient setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
        return this;
    }

    public String getAnswerFive() {
        return answerFive;
    }

    public Patient setAnswerFive(String answerFive) {
        this.answerFive = answerFive;
        return this;
    }

    public String getAnswerSix() {
        return answerSix;
    }

    public Patient setAnswerSix(String answerSix) {
        this.answerSix = answerSix;
        return this;
    }

    public String getAnswerSeven() {
        return answerSeven;
    }

    public Patient setAnswerSeven(String answerSeven) {
        this.answerSeven = answerSeven;
        return this;
    }

    public Date getSelfAssessmentTime() {
        return selfAssessmentTime;
    }

    public Patient setSelfAssessmentTime(Date selfAssessmentTime) {
        this.selfAssessmentTime = selfAssessmentTime;
        return this;
    }

    public Status getReviewStatus() {
        return reviewStatus;
    }

    public Patient setReviewStatus(Status reviewStatus) {
        this.reviewStatus = reviewStatus;
        return this;
    }

    public String getReviewer() {
        return reviewer;
    }

    public Patient setReviewer(String reviewer) {
        this.reviewer = reviewer;
        return this;
    }

    public String getAssignee() {
        return assignee;
    }

    public Patient setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public Patient() {
    }
}