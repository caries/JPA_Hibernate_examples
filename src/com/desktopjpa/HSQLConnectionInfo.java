package com.desktopjpa;

/**
 * Created by nickolay on 13/01/15.
 */
final class HSQLConnectionInfo {
    public static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    public static final String DB_NAME = "testdb";
    public static final String URL = "jdbc:hsqldb:mem:" + DB_NAME;
    public static final String USER = "SA";
    public static final String PASSWORD = "";
    public static final int PORT = 9001;
}
