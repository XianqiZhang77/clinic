package com.soen6841.demo.dao;

import com.soen6841.demo.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @Override
    Iterable<User> findAll();
    
    User findOneByUserID(String userID);
    
    boolean existsByUserID(String userID);

}