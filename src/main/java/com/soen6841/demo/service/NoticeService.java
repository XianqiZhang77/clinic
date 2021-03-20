package com.soen6841.demo.service;

import com.soen6841.demo.dao.NoticeRepository;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Notice;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NurseService nurseService;
    @Autowired
    private DoctorService doctorService;

    public Iterable<Notice> findNoticeByUserID(String userID) {

        List<Notice> notices = (List<Notice>) noticeRepository.findByPatientUserID(userID);

        Collections.sort(notices, (n1, n2)-> n1.getNoticeTime().before(n2.getNoticeTime()) ? 1 : -1);

        return notices;
    }

    public void cancelAppointmentByNurse(String patientUserID, String nurseUserID) {
        Notice notice = new Notice();
        Nurse nurse = nurseService.getNurseByUserID(nurseUserID);
        Date date = new Date();
        String message = DateFormatUtil.DateUtil(date)+" : " + "Sorry, your appointment with " + nurse.getFullName() + " (Nurse) was cancelled";
        notice.setNoticeTime(date).setPatientUserID(patientUserID)
                .setNurseUserID(nurseUserID).setNotice(message).setStatus(Status.cancelled);
        noticeRepository.save(notice);
    }

    public void cancelAppointmentByDoctor(String patientUserID, String doctorUserID) {
        Notice notice = new Notice();
        Doctor doctor = doctorService.getDoctorByUserID(doctorUserID);
        Date date = new Date();
        String message = DateFormatUtil.DateUtil(date)+" : " + "Sorry, your appointment with " + doctor.getFullName() + " (Nurse) was cancelled";
        notice.setNoticeTime(date).setPatientUserID(patientUserID)
                .setDoctorUserID(doctorUserID).setNotice(message).setStatus(Status.cancelled);
        noticeRepository.save(notice);
    }

    public void addAcceptNoticeByNurse(String patientUserID, String nurseUserID) {
        Notice notice = new Notice();
        Nurse nurse = nurseService.getNurseByUserID(nurseUserID);
        Date date = new Date();
        String message = DateFormatUtil.DateUtil(date) + " : " + nurse.getFullName() + " (Nurse) " + "made an appointment with you, please check";
        notice.setNoticeTime(date).setPatientUserID(patientUserID)
                .setNurseUserID(nurseUserID).setNotice(message).setStatus(Status.accepted);
        noticeRepository.save(notice);
    }
    public void addAcceptNoticeByDoctor(String patientUserID, String doctorUserID) {
        Notice notice = new Notice();
        Doctor doctor = doctorService.getDoctorByUserID(doctorUserID);
        Date date = new Date();
        String message = DateFormatUtil.DateUtil(date) + " : " + doctor.getFullName() + " (Doctor) " + "made an appointment with you, please check";
        notice.setNoticeTime(date).setPatientUserID(patientUserID)
                .setDoctorUserID(doctorUserID).setNotice(message).setStatus(Status.accepted);
        noticeRepository.save(notice);
    }

    public void addAssignNotice(String patientUserID, String nurseUserID, String doctorUserID) {
        Notice notice = new Notice();
        Nurse nurse = nurseService.getNurseByUserID(nurseUserID);
        Doctor doctor = doctorService.getDoctorByUserID(doctorUserID);
        Date date = new Date();
        String message = DateFormatUtil.DateUtil(date) + " : " + nurse.getFullName() + " (Nurse) " + "assigned you to Doctor " + doctor.getFullName()
                + " who will help you further";
        notice.setNoticeTime(date).setPatientUserID(patientUserID)
                .setNurseUserID(nurseUserID).setDoctorUserID(doctorUserID).setNotice(message);
        noticeRepository.save(notice);
    }

}
