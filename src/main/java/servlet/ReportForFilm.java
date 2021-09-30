package servlet;

import entity.Ticket;
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

@WebServlet("/reportForFilm")
public class ReportForFilm extends HttpServlet {
    private static final Logger log = Logger.getLogger(ReportForFilm.class);
    DAOFactory dao = null;
    @Override
    public void init() throws ServletException {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/filmReport.jsp");
        int id_film = Integer.parseInt(req.getParameter("film_id"));
        ArrayList<Ticket> ticks = dao.getOrderDAO().reportForFilm(id_film);
        ArrayList<Session> sessions = new ArrayList<>();
        for (Ticket s : ticks) {
            sessions.add(s.getSession());
        }
        long ses_count = sessions.stream().distinct().count();
        req.setAttribute("sessionCount", ses_count);
        req.setAttribute("film",dao.getFilmDAO().getFilm(id_film));
        req.setAttribute("tickets",ticks);
        requestDispatcher.forward(req,resp);
    }
}
