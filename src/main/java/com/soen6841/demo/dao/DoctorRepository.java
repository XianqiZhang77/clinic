package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    Iterable<Doctor> findAll();

    Doctor findOneById(Long id);

    Iterable<Doctor> findDoctorByRegisterStatus(Status registerStatus);

}
