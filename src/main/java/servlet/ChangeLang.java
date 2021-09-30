package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to get selected language and store in sessionScope
 */
@WebServlet("/changeLang")
public class ChangeLang extends HttpServlet {
    private static final Logger log = Logger.getLogger(ChangeLang.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("doGet start");
        String lang;
        lang = req.getParameter("lang");
        if (lang == null) {
            req.getSession().removeAttribute("session_lang");
            log.debug("Set session locale = UA");
        }
        if (lang != null && lang.equals("eng")) {
            req.getSession().setAttribute("session_lang", "eng");
            log.debug("Set session locale = ENG");
        }
        resp.sendRedirect("/");
        log.trace("doGet finish");
    }
}
