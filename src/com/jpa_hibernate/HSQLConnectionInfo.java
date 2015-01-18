package com.jpa_hibernate;

/**
 * Contains information about the connection to HSQL database.
 */
final class HSQLConnectionInfo {
    public static final String DB_NAME = "testdb";
    public static final String URL = "jdbc:hsqldb:mem:" + DB_NAME;
    public static final String USER = "SA";
    public static final String PASSWORD = "";
    public static final int PORT = 9001;
}
