package com.defender.test.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "subjects")
@Getter
public class Subject extends BaseEntity{
    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
