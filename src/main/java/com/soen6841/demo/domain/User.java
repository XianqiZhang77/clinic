package com.soen6841.demo.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "userID")
    private String userID;
    @Column(name = "password")
    private String password;
    @Column(name = "userType")
    private String userType;
    @Column(name = "register_id")
    private Long register_id;
    
    public User(String userID, String password, String userType, Long registerID) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.register_id = registerID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public Long getRegisterID() {
        return register_id;
    }
    
    public void setRegisterID(Long registerID) {
        this.register_id = registerID;
    }
    
    public User() {
    }
    
}
