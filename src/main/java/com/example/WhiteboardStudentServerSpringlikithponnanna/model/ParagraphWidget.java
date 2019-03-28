package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class ParagraphWidget extends Widget {
    private String pText;
    private String pName;

    public ParagraphWidget(String pText) {
        this.pText = pText;
    }

    public ParagraphWidget(Long id, String wType, Integer height, Integer width, String pText) {
        super(id, wType, height, width);
        this.pText = pText;
    }

    public ParagraphWidget() {
    }

    public String getpText() {
        return pText;
    }

    public void setpText(String pText) {
        this.pText = pText;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
