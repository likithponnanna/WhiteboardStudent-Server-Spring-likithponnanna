package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
private Long  id;
private  String title;
  @ManyToOne()
  @JsonIgnore
private User user;
  @OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Module> modules;

  public Course() {
  }

  public Course(String title, User user, List<Module> modules) {
    this.title = title;
    this.user = user;
    this.modules = modules;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List <Module> getModules() {
    return modules;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setModules(List <Module> modules) {
    this.modules = modules;
  }


}
