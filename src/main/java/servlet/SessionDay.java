package servlet;

import entity.session.Session;
import entity.session.comparators.SortSessionByDate;
import entity.session.comparators.SortSessionByFilmName;
import entity.session.comparators.SortSessionBySeats;
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
import java.util.HashMap;


/**
 * Create page with cinema sessionы for day
 */
@WebServlet("/sessions_for_day")
public class SessionDay extends HttpServlet {
    private static final Logger log = Logger.getLogger(SessionDay.class);
    boolean asc_order = true;
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
        String language = (String) req.getSession().getAttribute("session_lang");
        String sort_content = (String) req.getSession().getAttribute("sort_content");
        log.debug("Setting sessions for current week");
        ArrayList<Session> sessions = (ArrayList<Session>) dao.getSessionDAO().getSessionForDay(req.getParameter("day_of_week"));
        log.debug("Checking sorting parameters");
        if (req.getParameter("sort_content") != null) {
            sort_content = req.getParameter("sort_content");
        }
        if (sort_content != null && sort_content.equals("film")) {
            if (asc_order) {
                sessions = SortSessionByFilmName.sortAsc(sessions, language);
                asc_order = false;
            } else {
                sessions = SortSessionByFilmName.sortDesc(sessions, language);
                asc_order = true;
            }
            req.getSession().setAttribute("sort_content", "film");
            log.debug("Sorting by films name");
        }
        if (sort_content != null && sort_content.equals("seat")) {
            HashMap<Session, Integer> sessionsSeat;
            sessionsSeat = dao.getSessionDAO().getSessionSeats(sessions);
            if (asc_order) {
                sessionsSeat = SortSessionBySeats.sortAsc(sessionsSeat);
                asc_order = false;
            } else {
                sessionsSeat = SortSessionBySeats.sortDesc(sessionsSeat);
                asc_order = true;
            }
            req.getSession().setAttribute("sort_content", "seat");
            req.setAttribute("sessionsSeat", sessionsSeat);
            log.debug("Sorting by seats amount");
        }
        if (sort_content != null && sort_content == "") {
            if (asc_order) {
                sessions = SortSessionByDate.sortAsc(sessions);
                asc_order = false;
            } else {
                sessions = SortSessionByDate.sortDesc(sessions);
                asc_order = true;
            }
            req.getSession().removeAttribute("sort_content");
            log.debug("Sorting by default(date)");
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sessions_for_day.jsp");
        req.getSession().setAttribute("day", req.getParameter("day_of_week"));
        req.setAttribute("sessions", sessions);
        log.debug("Set days of week and sorted sessions");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }

}
