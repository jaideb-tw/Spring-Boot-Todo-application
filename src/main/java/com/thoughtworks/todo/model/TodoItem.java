package com.thoughtworks.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Todo")
public class TodoItem {


    private Long id;
    private String title;
    private Boolean isDone;

    public TodoItem(Long id, String title, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    public TodoItem() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
