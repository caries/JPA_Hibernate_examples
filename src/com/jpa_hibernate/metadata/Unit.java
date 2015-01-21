package com.jpa_hibernate.metadata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Encapsulates a simple unit
 */
@Entity
public class Unit implements Serializable {
    private long id;
    private String name;

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

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}