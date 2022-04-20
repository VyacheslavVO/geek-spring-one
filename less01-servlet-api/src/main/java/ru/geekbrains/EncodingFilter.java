package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // -- здесь можно сделать аутентификацию и авторизацию
        servletResponse.setContentType( "text/html" );
        servletResponse.setCharacterEncoding( "utf-8" );
        // пробросить фильтр дальше (передать основному приложению)
        filterChain.doFilter( servletRequest, servletResponse );
    }

    @Override
    public void destroy() {

    }
}
