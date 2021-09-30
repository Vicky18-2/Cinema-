package servlet;

import entity.film.Film;
import entity.film.Genre;
import org.apache.log4j.Logger;
import server.dao.DAOFactory;
import util.ConvertStringToDateSQL;
import util.GetDAOForServlet;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Servlet to handle film creation
 */
@WebServlet("/create_film")
@MultipartConfig
public class CreateFilmServlet extends HttpServlet {
    DAOFactory dao = null;
    private static final Logger log = Logger.getLogger(CreateFilmServlet.class);

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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/create_film.jsp");
        req.setAttribute("directors", dao.getFilmDAO().getAllDirector());
        req.setAttribute("genres", dao.getFilmDAO().getAllGenres());
        requestDispatcher.forward(req, resp);
        log.trace("doGet finish");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Genre> genres = new ArrayList<>();
        String[] genres_names = req.getParameterValues("genre_film");
        Part filePart = req.getPart("film_pic");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        BufferedImage bi = ImageIO.read(fileContent);
        String path = req.getSession().getServletContext().getRealPath("/images") + "\\" + fileName;
        ImageIO.write(bi, "jpg", new File(path));
        for (String s : genres_names
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
                .image(fileName)
                .build();
        dao.getFilmDAO().addFilm(newFilm);
        resp.sendRedirect("/");
    }
}
