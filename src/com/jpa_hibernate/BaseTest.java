package com.jpa_hibernate;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseTest {
    static protected EntityManager entityManager;

    @BeforeClass
    public static void setUp() throws Exception {
        HSQLServer.start();
        createTable();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    private static void createTable() throws SQLException {
        DriverManager.getConnection(HSQLConnectionInfo.URL, HSQLConnectionInfo.USER, HSQLConnectionInfo.PASSWORD)
                .createStatement().execute(Unit.CREATE_SCRIPT);
    }

    private static void dropTable() throws SQLException {
        DriverManager.getConnection(HSQLConnectionInfo.URL, HSQLConnectionInfo.USER, HSQLConnectionInfo.PASSWORD)
                .createStatement().execute(Unit.DROP_SCRIPT);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        dropTable();
        HSQLServer.stop();
    }
}