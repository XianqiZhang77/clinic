package com.soen6841.demo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patientUserID")
    private String patientUserID;
    @Column(name = "nurseUserID")
    private String nurseUserID;
    @Column(name = "doctorUserID")
    private String doctorUserID;
    @Column(name = "notice")
    private String notice;
    @Column(name = "Status")
    private Status status;
    @Column(name = "noticeTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM")
    @Temporal(TemporalType.DATE)
    private Date noticeTime;

    public Status getStatus() {
        return status;
    }

    public Notice setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public Notice setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Notice setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPatientUserID() {
        return patientUserID;
    }

    public Notice setPatientUserID(String patientUserID) {
        this.patientUserID = patientUserID;
        return this;
    }

    public String getNurseUserID() {
        return nurseUserID;
    }

    public Notice setNurseUserID(String nurseUserID) {
        this.nurseUserID = nurseUserID;
        return this;
    }

    public String getDoctorUserID() {
        return doctorUserID;
    }

    public Notice setDoctorUserID(String doctorUserID) {
        this.doctorUserID = doctorUserID;
        return this;
    }

    public String getNotice() {
        return notice;
    }

    public Notice setNotice(String notice) {
        this.notice = notice;
        return this;
    }
}
