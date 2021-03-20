package com.soen6841.demo.service;

import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.domain.Appointment;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentService appointmentService;

    public Iterable<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }
    
    public Nurse getNurseById(Long id) {
        return nurseRepository.findOneById(id);
    }

    public Nurse getNurseByUserID(String userID) {
        return nurseRepository.findOneByUserID(userID);
    }

    public Iterable<Patient> getAllAcceptedPatients() {
        return patientService.findAllAcceptedPatients();
    }

    public Iterable<Doctor> getAllAcceptedDoctors() {
        return doctorService.getAllAcceptedDoctors();
    }

    public boolean existsByUserID(String nurseUserID) {
        return nurseRepository.existsByUserID(nurseUserID);
    }
    
    public void MakeAppointment(Appointment appointment, String nurseUserID) {
    	appointment.setAppointmentStatus(Status.appointed);
    	appointment.setHealthCareID(nurseUserID);
    	
    	Patient patient = patientService.getPatientByUserID(appointment.getPatientUserID());
    	patient.setReviewStatus(Status.appointed);
    	patientService.savePatient(patient);
    	Nurse nurse =  getNurseByUserID(nurseUserID);
    	appointment.setHealthCareName(nurse.getFullName());
        appointmentService.saveAppointment(appointment);
    }
    
    public void CancelAppointment(String Id) {
    	Long number = Long.valueOf(Id);
    	
    	//Change Appointment Status
    	Appointment appointment = appointmentService.getAppointmentByID(number);
    	appointment.setAppointmentStatus(Status.cancelled);
    	appointmentService.saveAppointment(appointment);
    	
    	//Change Review Status
    	String patientUserID = appointment.getPatientUserID();
    	Patient patient = patientService.getPatientByUserID(patientUserID);
    	patient.setReviewStatus(Status.under_review);
    	patientService.savePatient(patient);
    }
}
