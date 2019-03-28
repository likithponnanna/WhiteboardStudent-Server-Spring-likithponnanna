package com.example.WhiteboardStudentServerSpringlikithponnanna.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class ListWidget extends Widget {
    private String items;
    private String listType;
    private String lName;


    public ListWidget() {
    }

    private Boolean ordered;

  /*  public ListWidget(String[] items, Boolean ordered) {
        this.items = items;
        this.ordered = ordered;
    }*/

  /*  public ListWidget(Long id, String wType, Integer height, Integer width, String[] items, Boolean ordered) {
        super(id, wType, height, width);
        this.items = items;
        this.ordered = ordered;
    }*/

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
