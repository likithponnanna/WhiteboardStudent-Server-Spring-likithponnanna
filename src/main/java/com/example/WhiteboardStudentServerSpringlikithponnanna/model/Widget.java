package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Widget {
  @Id
  @GeneratedValue
          (strategy= GenerationType.IDENTITY)
  private Long id;
  private String type;
  private Integer orderId;
  private Integer height;
  private Integer width;
  @ManyToOne
  @JsonIgnore
  private Topic topic;

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public Widget() {
  }

  public Widget(Long id, String wType, Integer height, Integer width) {
    this.id = id;
    this.type = wType;
    this.height = height;
    this.width = width;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
}
