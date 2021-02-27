package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {
    @Override
    Iterable<Patient> findAll();

    Patient findOneById(Long id);

}
