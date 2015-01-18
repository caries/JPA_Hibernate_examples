package com.jpa_hibernate;

import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Unit test for Unit class.
 */
public class UnitTest extends BaseTest {

    @Test
    public void testUnitPersisting() {
        Unit unit = new Unit();
        unit.setName("Yoho");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(unit);
            transaction.commit();
        } catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        List units = entityManager.createQuery("Select u from Unit u").getResultList();
        assertEquals(units.size(), 1);

        for (Object dbUnit : units) {
            System.out.println(dbUnit);
        }
    }
}