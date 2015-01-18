package com.desktopjpa;

import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.persistence.EntityTransaction;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nickolay on 17/01/15.
 */
public class UnitTest extends BaseTest {
    public UnitTest(String name) throws Exception {
        super(name);
    }

    public void testDataSourceInitialization() throws Exception {
        ITable actualTable = getConnection().createDataSet().getTable("Unit");
        ITable expectedTable = new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml")).getTable("Unit");
        Assertion.assertEquals(expectedTable, actualTable);
    }

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

        List<Unit> units = entityManager.createQuery("Select u from Unit u").getResultList();
        assertEquals(units.size(), 2);

        for (Unit dbUnit : units) {
            System.out.println(dbUnit);
        }
    }
}