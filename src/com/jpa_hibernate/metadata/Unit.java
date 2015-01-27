package com.jpa_hibernate.metadata;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates a simple unit
 */
@Entity
public class Unit implements Serializable {
    private long id;
    private String name;
    private List<String> comments;

    public Unit() {
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection
    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}