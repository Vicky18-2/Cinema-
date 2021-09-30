package servlet;

import entity.film.Genre;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.GetDAOForServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle Genre creation
 */
@WebServlet("/createGen")
public class CreateGen extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateGen.class);
    DAOFactory dao;

    @Override
    public void init() throws ServletException {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doPost start");
        String name = req.getParameter("name");
        Genre genre = new Genre(name);
        dao.getFilmDAO().addGenre(genre);
        resp.sendRedirect("/create_film");
        log.trace("doPost finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/createDir.jsp");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }
}
