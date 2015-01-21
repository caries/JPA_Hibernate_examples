package com.jpa_hibernate.metadata.office;

import javax.persistence.*;

/**
 * Contains employee's features.
 */
@Entity
public class Employee extends Person {
    private Company company;
    private Employee secretSantaVictim;

    public Employee() {
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id", table = "Employee")
    public Employee getSecretSantaVictim() {
        return secretSantaVictim;
    }

    public void setSecretSantaVictim(Employee secretSanta) {
        this.secretSantaVictim = secretSanta;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                "company=" + company +
                ", secretSantaVictim='" + secretSantaVictim.getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return company.getName().equals(employee.company.getName()) && secretSantaVictim.getName().equals(employee.secretSantaVictim.getName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + company.getName().hashCode();
        result = 31 * result + secretSantaVictim.getName().hashCode();
        return result;
    }
}