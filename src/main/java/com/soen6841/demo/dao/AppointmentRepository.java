package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    Iterable<Appointment> findAppointmentByHealthCareID(String healthCareID);

    Iterable<Appointment> findAppointmentByPatientUserID(String patientUserID);
}
