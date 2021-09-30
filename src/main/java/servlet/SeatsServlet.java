package servlet;

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
 * Servlet to create page about available seats on cinema session
 */
@WebServlet("/seats")
public class SeatsServlet extends HttpServlet {
    DAOFactory dao = null;
    private static final Logger log = Logger.getLogger(SeatsServlet.class);

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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/seats.jsp");
        int ses = Integer.parseInt(req.getParameter("session_id"));
        req.setAttribute("session_id", ses);
        req.setAttribute("seats", dao.getHallDAO().getColumns());
        log.debug("Set unbooked seats for session");
        req.getSession().setAttribute("booked_seats", dao.getHallDAO().
                getBookedSeats(dao.getSessionDAO().getSession(ses)));
        log.debug("Set booked seats");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }
}
