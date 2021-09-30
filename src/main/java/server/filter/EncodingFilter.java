package server.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * Encoding filter to get information in UTF-8 from html
 */
public class EncodingFilter implements Filter {
    private String encoding;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig fConfig){
        encoding = fConfig.getInitParameter("encoding");
    }

}
