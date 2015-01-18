package com.jpa_hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by nickolay on 12/01/15.
 * Table script 'create table Unit (id int IDENTITY, name varchar(256))'
 */
@Entity
public class Unit implements Serializable {
    final static String CREATE_SCRIPT = "create table Unit (id int IDENTITY, name varchar(256))";
    final static String DROP_SCRIPT = "drop table Unit";

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