package com.jpa_hibernate.tests;

import com.jpa_hibernate.utils.HSQLServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseTest {
    static protected EntityManager entityManager;

    @BeforeClass
    public static void setUp() throws Exception {
        HSQLServer.start();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HSQLServer.stop();
    }
}