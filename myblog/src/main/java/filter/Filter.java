package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class Filter implements javax.servlet.Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
