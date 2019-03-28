package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {
    public Student(Integer gradYear, Long scholarship) {
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }

    public Student() {
    }

    public Student(Long userId, String username, String password, String firstName, String lastName, String role, String email, String phoneNo, Integer gradYear, Long scholarship) {
        super(userId, username, password, firstName, lastName, role, email, phoneNo);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }

    private Integer gradYear;
    private Long scholarship;

    public Integer getGradYear() {
        return gradYear;
    }

    public void setGradYear(Integer gradYear) {
        this.gradYear = gradYear;
    }

    public Long getScholarship() {
        return scholarship;
    }

    public void setScholarship(Long scholarship) {
        this.scholarship = scholarship;
    }
}
