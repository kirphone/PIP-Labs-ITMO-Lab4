package com.itmo.lab4.backend;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        ((HttpServletResponse) res).setHeader("Access-Control-Max-Age", "3600");
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
}
