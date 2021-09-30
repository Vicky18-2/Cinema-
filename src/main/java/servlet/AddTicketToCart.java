package servlet;

import entity.Ticket;
import entity.hall.Column;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.GetDAOForServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet to add ticket to session scope list of tickets
 */
@WebServlet("/addToCart")
public class AddTicketToCart extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddTicketToCart.class);
    DAOFactory dao = null;

    @Override
    public void init() {
        log.trace("init started");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        log.debug("Adding new ticket to order");
        Column col = dao.getHallDAO().getColumn(Integer.parseInt(req.getParameter("id_col")));
        int id_ses = Integer.parseInt(req.getParameter("ses_id"));
        List<Ticket> tickets = (List<Ticket>) req.getSession().getAttribute("tickets");
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        Ticket newTicket = new Ticket(dao.getSessionDAO().getSession(id_ses),
                col.getSeats().get(Integer.parseInt(req.getParameter("id_seat")) - 1), col);
        tickets.add(newTicket);
        req.getSession().setAttribute("tickets", tickets);
        resp.sendRedirect("/seats" + "?session_id=" + id_ses);
        log.trace("doGet finish");
    }
}
