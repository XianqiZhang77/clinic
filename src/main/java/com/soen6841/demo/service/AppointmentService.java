package com.soen6841.demo.service;

import com.soen6841.demo.dao.AppointmentRepository;
import com.soen6841.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Iterable<Appointment> getAllAssignedByHealthCareID(String userID) {
        return appointmentRepository.findAppointmentByHealthCareID(userID);
    }
    
    public Iterable<Appointment> getAllAssignedByPatientUserID(String userID) {
        return appointmentRepository.findAppointmentByPatientUserID(userID);
    }
    
    public Appointment getAppointmentByID(Long ID) {
        return appointmentRepository.findOneById(ID);
    }



}
