package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class ImageWidget extends Widget {
    private String src;
    private String iName;

    public ImageWidget() {
    }

    public ImageWidget(String src) {
        this.src = src;
    }

    public ImageWidget(Long id, String wType, Integer height, Integer width, String src) {
        super(id, wType, height, width);
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
