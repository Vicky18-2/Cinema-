package servlet;

import entity.order.Order;
import entity.user.User;
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

@WebServlet("/orderUser")
public class OrdersUser extends HttpServlet {
    DAOFactory dao = null;
    @Override
    public void init() throws ServletException {
        dao = GetDAOForServlet.getDAO(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userOrders.jsp");
        User user = dao.getUserDAO().getUserByEmail((String) req.getSession().getAttribute("session_email"));
        ArrayList<Order> orders = dao.getOrderDAO().getOrdersForUser(user);
        req.setAttribute("orders", orders);
        requestDispatcher.forward(req, resp);
    }
}
