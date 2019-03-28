package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class LinkWidget extends Widget {
    private String title;
    private String lText;
    private String href;
    private String linkName;


    public LinkWidget() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getlText() {
        return lText;
    }

    public void setlText(String lText) {
        this.lText = lText;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}
