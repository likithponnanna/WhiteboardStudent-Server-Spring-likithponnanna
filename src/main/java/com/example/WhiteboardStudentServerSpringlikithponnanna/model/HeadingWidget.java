package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class HeadingWidget extends Widget {
    private Integer size;
    private String hName;
    private String text;

    public HeadingWidget(Integer size) {
        this.size = size;
    }

    public HeadingWidget(Long id, String wType, Integer height, Integer width, Integer size) {
        super(id, wType, height, width);
        this.size = size;
    }

    public HeadingWidget() {
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
