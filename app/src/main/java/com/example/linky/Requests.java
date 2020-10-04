package com.example.linky;

public class Requests {
    String ReqId,description,email,location,nameOfApplicant,peoples,phone;

    public Requests() {
    }

    public Requests(String reqId, String description, String email, String location, String nameOfApplicant, String peoples, String phone) {
        ReqId = reqId;
        this.description = description;
        this.email = email;
        this.location = location;
        this.nameOfApplicant = nameOfApplicant;
        this.peoples = peoples;
        this.phone = phone;
    }

    public String getNameOfApplicant() {
        return nameOfApplicant;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }

    public String getReqId() {
        return ReqId;
    }

    public void setReqId(String reqId) {
        ReqId = reqId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPeoples() {
        return peoples;
    }

    public void setPeoples(String peoples) {
        this.peoples = peoples;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
