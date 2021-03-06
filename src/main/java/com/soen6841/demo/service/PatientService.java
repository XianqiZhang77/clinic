package com.soen6841.demo.service;

        import com.soen6841.demo.dao.PatientRepository;
        import com.soen6841.demo.domain.Patient;
        import com.soen6841.demo.domain.Status;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import javax.swing.*;
        import java.util.Iterator;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findOneById(id);
    }

    public Patient getPatientByUserID(String userID) {
        return patientRepository.findOneByUserID(userID);
    }

    public Patient saveQuestionAnswers(String patientId, String[] answer) {
        if(patientRepository.existsByUserID(patientId)){
            patientRepository.findOneByUserID(patientId).setAnswerOne(answer[0]);
            patientRepository.findOneByUserID(patientId).setAnswerTwo(answer[1]);
            patientRepository.findOneByUserID(patientId).setAnswerThree(answer[2]);
            patientRepository.findOneByUserID(patientId).setAnswerFour(answer[3]);
            patientRepository.findOneByUserID(patientId).setAppointmentStatus(Status.wating);
        }
        return patientRepository.findOneByUserID(patientId);
    }

    public String[] getQuestionAnswers(String patientId) {
        String[] results = new String[4];
        if(patientRepository.existsByUserID(patientId)){
            Patient check = patientRepository.findOneByUserID(patientId);
            results[0] = check.getAnswerOne();
            results[1] = check.getAnswerTwo();
            results[2] = check.getAnswerThree();
            results[3] = check.getAnswerFour();
        }
        return results;
    }


}
