package util;

import server.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to get DAOFactory for exact database from settings file
 */
public class GetDAOForServlet extends HttpServlet {
    /**
     * @param servletContext to get real path
     * @return created DAOFactory
     */
    public static DAOFactory getDAO(ServletContext servletContext) {
        DAOFactory dao;
        Properties properties = new Properties();
        File file = new File(servletContext.getRealPath("/WEB-INF/settings.properties"));
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao = DAOFactory.getDAOFactoryFromSettings(properties);
        return dao;
    }
}
