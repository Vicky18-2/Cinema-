package server.dao;

import server.dao.mysql.MysqlDAOFactory;

import java.util.Properties;

public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int WSFACTORY = 2;
    private static TypeDAO defaultFactory = TypeDAO.MySQL;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract FilmDAO getFilmDAO();

    public abstract HallDAO getHallDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract SessionDAO getSessionDAO();

    public abstract UserDAO getUserDAO();


    public static DAOFactory getDAOFactory(TypeDAO whichFactory) {
        switch (whichFactory) {
            case MySQL:
                return new MysqlDAOFactory();
//		case TypeDAO.Mongo:
//			return new MongoDAOFactory();
            default:
                return null;
        }
    }

    public static DAOFactory getDAOFactoryFromSettings(Properties properties) {
        TypeDAO whichFactory;
        whichFactory = TypeDAO.valueOf(properties.getProperty("dataBase"));
        switch (whichFactory) {
            case MySQL:
                return new MysqlDAOFactory();
//		case TypeDAO.Mongo:
//			return new MongoDAOFactory();
            default:
                return null;
        }
    }

    public static DAOFactory getDAOFactory() {
        return getDAOFactory(defaultFactory);
    }

    public static void setDefaultFactory(TypeDAO whichFactory) {
        defaultFactory = whichFactory;
    }

}
