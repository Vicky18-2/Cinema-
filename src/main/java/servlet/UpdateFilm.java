package servlet;

import entity.film.Film;
import entity.film.Genre;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.ConvertStringToDateSQL;
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

@WebServlet("/update_film")
public class UpdateFilm extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateFilm.class);
    DAOFactory dao = null;

    @Override
    public void init() throws ServletException {
        log.trace("init start");
        dao = GetDAOForServlet.getDAO(this.getServletContext());
        log.trace("init finish");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/update_film.jsp");
        int film_id = Integer.parseInt(req.getParameter("film_id"));
        req.setAttribute("film", dao.getFilmDAO().getFilm(film_id));
        req.setAttribute("directors", dao.getFilmDAO().getAllDirector());
        req.setAttribute("genres", dao.getFilmDAO().getAllGenres());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Genre> genres = new ArrayList<>();
        String[] asd = req.getParameterValues("genre_film");
        for (String s : asd
        ) {
            genres.add(dao.getFilmDAO().getGenre(Integer.parseInt(s)));
        }
        Film newFilm = new Film.Builder()
                .name(req.getParameter("film_name"))
                .nameUa(req.getParameter("film_nameUa"))
                .description(req.getParameter("description"))
                .descriptionUa(req.getParameter("descriptionUa"))
                .date(ConvertStringToDateSQL.convert(req.getParameter("release")))
                .directors(dao.getFilmDAO().
                        getDirectorById(Integer.parseInt(req.getParameter("director_film"))))
                .duration(Integer.parseInt(req.getParameter("duration")))
                .genres(genres)
                .build();
        Film oldFilm = dao.getFilmDAO().getFilm(Integer.parseInt(req.getParameter("film_id")));
        oldFilm = oldFilm.updateFilm(newFilm);
        dao.getFilmDAO().updateFilm(oldFilm);
        resp.sendRedirect("/");
    }
}
