package server.dao.mysql;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import server.dao.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * <p>Concrete factory for MySQL database.</p>
 * <p>Contains methods for create each domain DAO and methods to manage database connection.</p>
 *
 * @author engsyst
 * @see DAOFactory
 */
public class MysqlDAOFactory extends DAOFactory {
    private static final Logger log = Logger.getLogger(MysqlDAOFactory.class);

    public MysqlDAOFactory() {
        BasicConfigurator.configure();
    }

    /**
     * Get connection from connection pool using properties from META-INF/context.xml
     *
     * @return Connection to MySQL database
     * @throws SQLException           if error occured during initialising Connection
     * @throws ClassNotFoundException if cannot find driver for DriverManager to connect to MySQL
     */
    static Connection getConnection() {
        log.trace("Start");
        Connection con = null;
        try{
            con = HikariCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        try {
                con = DriverManager.getConnection("jdbc:mysql://mysql-21269-0.cloudclusters.net:21269/cinemaweb?serverTimezone=UTC", "yehorka", "egor2906");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        log.trace("Finish");
            return con;
        }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                log.debug("Try rollback.");
                con.rollback();
            } catch (SQLException e) {
                log.error("Can not rollback transaction.", e);
            }
        }
    }

    protected static void commit(Connection con) {
        try {
            log.debug("Try commit transaction");
            con.commit();
        } catch (SQLException e) {
            log.error("Can not commit transaction." + e);
            try {
                log.debug("Try rollback transaction.");
                con.rollback();
            } catch (SQLException e1) {
                log.error("Can not rollback transaction." + e1);
            }
        }
    }

    protected static void close(Connection con) {
        try {
            log.debug("Try close connection.");
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            log.error("Can not close connection." + e.getMessage());
        }
    }

    static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                log.debug("Try close statement");
                stmt.close();
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                log.debug("try close resultSet");
                rs.close();
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    static void setAutocommit(Connection con, Boolean bool) {
        if (con != null) {
            try {
                con.setAutoCommit(bool);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return UserMySQL.getInstance();
    }

    @Override
    public FilmDAO getFilmDAO() {
        return FilmMySQL.getInstance();
    }

    @Override
    public SessionDAO getSessionDAO() {
        return SessionMySQL.getInstance();
    }

    @Override
    public HallDAO getHallDAO() {
        return HallMySQL.getInstance();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return OrderMySQL.getInstance();
    }
}
