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
    @Column(name = "registerStatus")
    private int registerStatus;

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
    
    public int getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(int registerStatus) {
        this.registerStatus = registerStatus;
    }
}
