package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import javax.persistence.Entity;

@Entity
public class Faculty extends User {
    public Faculty(String office, boolean tenured) {
        this.office = office;
        this.tenured = tenured;
    }

    public Faculty() {
    }

    public Faculty(Long userId, String username, String password, String firstName, String lastName, String role, String email, String phoneNo, String office, boolean tenured) {
        super(userId, username, password, firstName, lastName, role, email, phoneNo);
        this.office = office;
        this.tenured = tenured;
    }

    private String office;
    private boolean tenured;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }
}
