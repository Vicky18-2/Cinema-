package servlet;

import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.GetDAOForServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFilm")
public class DeleteFilm extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteFilm.class);
    DAOFactory dao = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    public void init() throws ServletException {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doPost start");
        int film_id = Integer.parseInt(req.getParameter("film_id"));
        log.debug("deleting film, id: "+film_id);
        dao.getFilmDAO().deleteFilm(film_id);
        resp.sendRedirect("/");
        log.trace("doPost finished");
    }
}
