package servlet;

import entity.session.Session;
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
import java.util.ArrayList;

/**
 * Create page with cinema sessions for film
 */
@WebServlet("/session_for_film")
public class SessionForFilm extends HttpServlet {
    private static final Logger log = Logger.getLogger(SessionForFilm.class);
    DAOFactory dao = null;

    @Override
    public void init() {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        ServletContext servletContext = getServletContext();
        int id_film = Integer.parseInt(req.getParameter("id_film"));
        ArrayList<Session> sessions = dao.getSessionDAO().getSessionForFilm(id_film);
        req.setAttribute("sessions", sessions);
        req.setAttribute("film", dao.getFilmDAO().getFilm(id_film));
        log.debug("Sessions -->" + sessions);
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sessions_for_film.jsp");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }
}
