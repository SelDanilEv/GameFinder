package com.defender.test.models;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Double prize;
    @ManyToMany
    @JoinTable(
            name = "participants",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "championship_id"))
    private Set<User> participants;

    public Championship() {
    }

    public Championship(Integer id, String name, Double prize, Set<User> participants) {
        this.id = id;
        this.name = name;
        this.prize = prize;
        this.participants = participants;
    }

    public Championship(String name, Double prize, Set<User> participants) {
        this.name = name;
        this.prize = prize;
        this.participants = participants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
