package server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to check if user is authenticated before adding tickets to cart or making order
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("ses_role");
        if (role == null) {
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            chain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {

    }

}
