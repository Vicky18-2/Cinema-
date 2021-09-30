package servlet;

import entity.Ticket;
import entity.order.Order;
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
import java.util.List;

/**
 * Servlet to handle order creation
 */
@WebServlet("/makeOrder")
public class MakeOrder extends HttpServlet {
    private static final Logger log = Logger.getLogger(MakeOrder.class);
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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/orderDetails.jsp");
        log.debug("Get to the order details page");
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order((List<Ticket>) req.getSession().getAttribute("tickets"),
                dao.getUserDAO().getUserByEmail((String) req.getSession().getAttribute("session_email")));
        dao.getOrderDAO().createOrder(order);
        log.debug("Order created");
        req.getSession().removeAttribute("tickets");
        resp.sendRedirect("/");
    }
}
