package com.jecrc_university.ju_mess;

public class MyUserData {

    String hostlerStatus,registrationNo,contactNo,gender,fullName;
    public MyUserData() {
    }
    public MyUserData(String hostlerStatus, String registrationNo,
                      String contactNo, String gender, String fullName) {
        this.hostlerStatus = hostlerStatus;
        this.registrationNo = registrationNo;
        this.contactNo = contactNo;
        this.gender = gender;
        this.fullName = fullName;
    }
    public String getHostlerStatus() {
        return hostlerStatus;
    }
    public void setHostlerStatus(String hostlerStatus) {
        this.hostlerStatus = hostlerStatus;
    }
    public String getRegistrationNo() {
        return registrationNo;
    }
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
