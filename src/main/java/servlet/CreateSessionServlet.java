package servlet;

import entity.session.Session;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import java.util.List;


/**
 * Servlet to handle cinema session creation
 */
@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
    DAOFactory dao = null;
    private static final Logger log = Logger.getLogger(CreateSessionServlet.class);

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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/createSession.jsp");
        String day = (String) req.getSession().getAttribute("day");
        List<Session> sessions = dao.getSessionDAO().getSessionForDay(day);
        DateTime minDate;
        DateTime maxDate = new DateTime(DateTime.now().getYear()
                , Integer.parseInt(day.split("\\.")[1]),
                Integer.parseInt(day.split("\\.")[0]), 22, 0);
        log.debug("Set max date is current day until 10pm");
        if (sessions.isEmpty()) {
            minDate = new DateTime(DateTime.now().getYear()
                    , Integer.parseInt(day.split("\\.")[1]),
                    Integer.parseInt(day.split("\\.")[0]), 0, 0);
            minDate = minDate.plusHours(9);
            log.debug("Set max date is current day from 9am");
        } else {
            Session last = sessions.get(sessions.size() - 1);
            minDate = last.getDateTime();
            minDate = minDate.plusMinutes(20 + last.getFilm().getDuration());
            log.debug("Set min date after duration of film before + 20 min");
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");
        req.setAttribute("minDate", fmt.print(minDate));
        req.setAttribute("maxDate", fmt.print(maxDate));
        req.setAttribute("films", dao.getFilmDAO().getAllFilms());
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doPost start");
        DateTime date = new DateTime(req.getParameter("date_get"));
        if (date.getHourOfDay() < 22 && date.getHourOfDay() >= 9) {
            Session getSession = new Session(dao.getHallDAO().getHall(1),
                    dao.getFilmDAO().getFilm(Integer.parseInt(req.getParameter("film_get"))), new DateTime(req.getParameter("date_get")));
            if(req.getParameter("blocked")!=null){
                dao.getSessionDAO().makeSessionWithRestrictions(getSession);
            }
            else {
                dao.getSessionDAO().addSession(getSession);
            }

            log.debug("Send created session to db");
        }
        resp.sendRedirect("/sessions_for_day" + "?day_of_week=" + req.getParameter("day"));
        log.trace("doPost finish");
    }
}
