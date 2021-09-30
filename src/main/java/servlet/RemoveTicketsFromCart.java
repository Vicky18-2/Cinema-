package servlet;

import entity.Ticket;
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

@WebServlet("/removeTicket")
public class RemoveTicketsFromCart extends HttpServlet {
    private static final Logger log = Logger.getLogger(RemoveTicketsFromCart.class);
    DAOFactory dao = null;

    @Override
    public void init() {
        log.trace("init started");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_ticket = Integer.parseInt(req.getParameter("ticket_id"));
        Ticket ticket = new Ticket();
        ticket.setId(id_ticket);
        ArrayList<Ticket> cart = (ArrayList<Ticket>) req.getSession().getAttribute("tickets");
        cart.remove(ticket);
        req.getSession().setAttribute("tickets",cart);
        resp.sendRedirect("/makeOrder");
    }
}
