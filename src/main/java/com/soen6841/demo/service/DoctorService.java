package com.soen6841.demo.service;

import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.domain.Appointment;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NoticeService noticeService;

    public Iterable<Doctor> getAllDoctors() {
        List<Doctor> doctors = (List<Doctor>) doctorRepository.findAll();
        Collections.sort(doctors, (d1, d2) -> {
            if (d1.getRegisterTime().before(d2.getRegisterTime())) {
                return 1;
            }
            if (d1.getRegisterTime().after(d2.getRegisterTime())) {
                return -1;
            }
            return 0;
        });
        return doctors;
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

    public boolean existsByUserID(String doctorUserID) {
        return doctorRepository.existsByUserID(doctorUserID);
    }

    public Iterable<Patient> getAllAcceptedPatients() {
        return patientService.findAllAcceptedPatients();
    }

    public Iterable<Doctor> getAllAcceptedDoctors() {
        return doctorRepository.findDoctorByRegisterStatus(Status.accepted);
    }

    public void MakeAppointment(Appointment appointment, String doctorUserID) {
    	
    	appointment.setAppointmentStatus(Status.appointed);
    	appointment.setHealthCareID(doctorUserID);
    	
    	Patient patient = patientService.getPatientByUserID(appointment.getPatientUserID());
    	patient.setReviewStatus(Status.appointed);
    	patientService.savePatient(patient);
    	Doctor doctor =  getDoctorByUserID(doctorUserID);
    	appointment.setHealthCareName(doctor.getFullName());
        appointmentService.saveAppointment(appointment);
        noticeService.addAcceptNoticeByDoctor(patient.getUserID(), doctorUserID);
    }
    
    public void CancelAppointment(String Id) {
    	Long number = Long.valueOf(Id);
        Appointment appointment = appointmentService.getAppointmentByID(number);
    	changeAppointmentAndReviewStatus(appointment);
    }

    public void cancelAppointmentByManager(String doctorId) {
        //Change Appointment Status
        Iterable<Appointment> appointments = appointmentService.getAllAssignedByHealthCareID(doctorId);
        for(Appointment appointment:appointments){
            changeAppointmentAndReviewStatus(appointment);
            //send a notice to patient
            String patientUserID = appointment.getPatientUserID();
            noticeService.cancelAppointmentByDoctor(patientUserID, appointment.getHealthCareID());
        }
    }

    public void changeAppointmentAndReviewStatus(Appointment appointment){
        //Change Appointment Status
        appointment.setAppointmentStatus(Status.cancelled);
        appointmentService.saveAppointment(appointment);

        //Change Review Status
        String patientUserID = appointment.getPatientUserID();
        Patient patient = patientService.getPatientByUserID(patientUserID);
        patient.setReviewStatus(Status.under_review);
        patientService.savePatient(patient);


    }


}
