package server.dao.mysql;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Stores method to get connection from MysqlDAOFactory class
 */
public interface Connectable {
    Logger log = Logger.getLogger(HallMySQL.class);

    default Connection getConnection() {
        return MysqlDAOFactory.getConnection();
    }
}
