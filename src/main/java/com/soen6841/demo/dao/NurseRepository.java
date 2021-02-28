package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends CrudRepository<Nurse, Long> {
    @Override
    Iterable<Nurse> findAll();

    Iterable<Nurse> findNurseByRegisterStatus(Status registerStatus);

    Nurse findOneById(Long valueOf);
}
