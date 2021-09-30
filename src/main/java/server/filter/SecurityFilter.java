package server.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to not allow users to get into admin pages
 */
@WebFilter(filterName = "server.filter.SecurityFilter")
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("ses_role");
        if (role != "ADMIN") {
            ((HttpServletResponse) response).sendRedirect("/");
        } else {
            chain.doFilter(req, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
