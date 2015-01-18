package com.desktopjpa;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class BaseTest extends DBTestCase {
    protected EntityManager entityManager;

    @Override
    protected void setUp() throws Exception {
        // TODO: Move it to BeforeClass. JUnit BeforeClass annotation doesn't work with DbUnit for some reason
        HSQLServer.start();
        createTable();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();

        super.setUp();
    }

    private static void createTable() throws SQLException {
        DriverManager.getConnection(HSQLConnectionInfo.URL, HSQLConnectionInfo.USER, HSQLConnectionInfo.PASSWORD)
                .createStatement().execute(Unit.CREATE_SCRIPT);
    }

    private static void dropTable() throws SQLException {
        DriverManager.getConnection(HSQLConnectionInfo.URL, HSQLConnectionInfo.USER, HSQLConnectionInfo.PASSWORD)
                .createStatement().execute(Unit.DROP_SCRIPT);
    }

    @Override
    protected void tearDown() throws Exception {
        dropTable();
        HSQLServer.stop();
        super.tearDown();
    }

    public BaseTest(String name) throws Exception {
        super(name);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, HSQLConnectionInfo.DRIVER);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, HSQLConnectionInfo.URL);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, HSQLConnectionInfo.USER);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, HSQLConnectionInfo.PASSWORD);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
    }
}