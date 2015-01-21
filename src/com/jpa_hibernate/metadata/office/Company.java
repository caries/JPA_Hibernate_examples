package com.jpa_hibernate.metadata.office;

import javax.persistence.*;
import java.util.List;

/**
 * Contains information about the company.
 */
@Entity
public class Company {
    private long id;
    private String name;
    private List<Employee> employees;
    private Government government;

    public Company() {
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

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id", table = "Government")
    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return id == company.id && employees.equals(company.employees) && government.equals(company.government) && name.equals(company.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + employees.hashCode();
        result = 31 * result + government.hashCode();
        return result;
    }
}