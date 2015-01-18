package com.jpa_hibernate;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.hsqldb.server.ServerConfiguration;

import java.io.IOException;

/**
 * Wraps HSQL server operations.
 */
final class HSQLServer {
    private static Server server;

    public static void start() throws IOException, ServerAcl.AclFormatException {
        HsqlProperties properties = getProperties();
        ServerConfiguration.translateDefaultDatabaseProperty(properties);

        server = new Server();
        server.setRestartOnShutdown(false);
        server.setNoSystemExit(true);
        server.setProperties(properties);

        server.start();
    }

    private static HsqlProperties getProperties() {
        HsqlProperties properties = new HsqlProperties();
        properties.setProperty("server.port", HSQLConnectionInfo.PORT);
        properties.setProperty("server.database.0", HSQLConnectionInfo.DB_NAME);
        properties.setProperty("server.dbname.0", HSQLConnectionInfo.DB_NAME);
        return properties;
    }

    public static void stop() {
        if (server == null) {
          throw new IllegalStateException("Server is not started");
        }

        server.stop();
    }
}