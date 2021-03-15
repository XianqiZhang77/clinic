package com.soen6841.demo.service;

import com.soen6841.demo.dao.AppointmentRepository;
import com.soen6841.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    NurseService nurseService;

    public boolean addNewAppointmentByAssign(String patientUserID, String doctorUserID, String nurseUserID) {

        if (!patientService.existsByUserID(patientUserID) || !doctorService.existsByUserID(doctorUserID) || !nurseService.existsByUserID(nurseUserID)) {
            return false;
        }
        Patient patient = patientService.getPatientByUserID(patientUserID);
        Doctor doctor = doctorService.getDoctorByUserID(doctorUserID);
        Appointment appointment = new Appointment();
        appointment.setPatient(patient.getFullName());
        appointment.setPatientUserID(patientUserID);
        appointment.setDoctor(doctor.getFullName());
        appointment.setDoctorUserID(doctorUserID);
        if (appointmentRepository.save(appointment) == null) {
            return false;
        }
        patientService.setReviewStatus(patient, Status.reviewed);
        return true;
    }

    public Iterable<Appointment> getAllAssignedByDoctorUserID(String userID) {
        return appointmentRepository.findAppointmentByDoctorUserID(userID);
    }
}
