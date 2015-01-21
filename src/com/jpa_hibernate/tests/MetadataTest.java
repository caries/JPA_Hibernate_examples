package com.jpa_hibernate.tests;

import com.jpa_hibernate.metadata.Unit;
import com.jpa_hibernate.metadata.office.Employee;
import com.jpa_hibernate.metadata.office.Government;
import com.jpa_hibernate.metadata.office.Company;
import com.jpa_hibernate.metadata.office.Person;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Unit test for metadata classes.
 */
public class MetadataTest extends BaseTest {
    @Test
    public void testUnitPersisting() throws Exception {
        final Unit unit = new Unit();
        unit.setName("Yoho");

        runTransactionSession(new TransactionSession() {
            @Override
            public void run(EntityManager entityManager) {
                entityManager.persist(unit);
            }
        });

        List units = entityManager.createQuery("Select u from Unit u").getResultList();
        assertEquals(units.size(), 1);
    }

    @Test
    public void testOfficeStructure() throws Exception {
        Government government = new Government();
        Person chief = new Person();
        chief.setName("Peter");
        government.setChief(chief);

        final Company company = new Company();
        company.setGovernment(government);
        company.setName("Icecream&co");
        company.setEmployees(new ArrayList<Employee>());

        addNewEmployees(company, "Bob", "Kate", "Luice", "Rob", "Obama", "Robin");
        setSecretSantaVictim(company.getEmployees());

        runTransactionSession(new TransactionSession() {
            @Override
            public void run(EntityManager entityManager) {
                entityManager.persist(company);
            }
        });

        Government persistedGovernment = (Government) entityManager.createQuery("Select g from Government g").getSingleResult();
        assertEquals(persistedGovernment, government);

        Company persistedCompany = (Company) entityManager.createQuery("Select c from Company c").getSingleResult();
        assertEquals(persistedCompany, company);

        Employee obama = findEmployee(company.getEmployees(), "Obama");
        Employee persistedObama = (Employee) entityManager.createQuery("Select emp from Employee emp where emp.name = 'Obama'").getSingleResult();
        assertEquals(persistedObama, obama);
    }

    private static void addNewEmployees(Company company, String... names) {
        for (String name : names) {
            addNewEmployee(company, name);
        }
    }

    private static void addNewEmployee(Company company, String name) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setCompany(company);
        company.getEmployees().add(employee);
    }

    private static void setSecretSantaVictim(List<Employee> employees) {
        List<Employee> praisedEmployees = new ArrayList<Employee>(employees);
        Random random = new Random();
        for (Employee employee : employees) {
            Employee praisedEmployee;
            do {
                praisedEmployee = praisedEmployees.get(random.nextInt(praisedEmployees.size()));
            } while (employee.getName().equals(praisedEmployee.getName()));

            employee.setSecretSantaVictim(praisedEmployee);
            praisedEmployees.remove(praisedEmployee);
        }
    }

    private static Employee findEmployee(List<Employee> employees, String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }

        return null;
    }

    private static void runTransactionSession(TransactionSession session) throws Exception {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            session.run(entityManager);
            transaction.commit();
        } catch(Exception e) {
            try {
                throw e;
            } finally {
                transaction.rollback();
            }
        }
    }

    private interface TransactionSession {
        void run(EntityManager entityManager);
    }
}