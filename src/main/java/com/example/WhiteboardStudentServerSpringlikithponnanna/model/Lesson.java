package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
private  Long id;
private  String title;
  @OneToMany(mappedBy="lesson", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Topic> topics;
  @ManyToOne
  @JsonIgnore
  private Module module;

  public Lesson() {
  }

  public Lesson(Long id, String title, List<Topic> topics) {
    this.id = id;
    this.title = title;
    this.topics  = topics;
  }

  @Transient
  public Long getModuleId() {
    return module.getId();
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

  public List <Topic> getTopics() {
    return topics;
  }

  public void setTopics(List <Topic> topics) {
    this.topics = topics;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }
}
