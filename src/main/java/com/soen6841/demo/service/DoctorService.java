package com.soen6841.demo.service;

import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findOneById(id);
    }
    
    public Doctor getDoctorByUserID(String userID) {
        return doctorRepository.findOneByUserID(userID);
    }
    
    public Iterable<Patient> getPatientByAppoinmentStatus(Status registerStatus) {
        return patientRepository.findPatientByAppointmentStatus(registerStatus);
    }
    
    public Iterable<Patient> getPatientByAssignee(String assignee) {
        return patientRepository.findPatientByAssignee(assignee);
    }
}
