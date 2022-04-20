package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private static final Pattern PARAM_PATTERN = Pattern.compile( "\\/(\\d+)" );

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute( "userRepository" );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("<h1>Привет от сервлета!!!</h1>");
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        PrintWriter wr = resp.getWriter();

        if(req.getPathInfo().equals( "/") || req.getPathInfo() == null) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Username</th>");
            wr.println("</tr>");
            for (User user : userRepository.findAll()) {
                wr.println( "<tr>" );
                // с параметрами
                //wr.println( "<td><a href='" + user.getId() + "?id=" + user.getId() + "&name=" + user.getUsername() + "'>" + user.getId() + "</a></td>" );
                // чистый URL
                wr.println( "<td><a href='" + getServletContext().getContextPath() + "/user/" + user.getId() + "'>" + user.getId() + "</a></td>" );
                wr.println( "<td>" + user.getUsername() + "</td>" );
                wr.println( "</tr>" );
            }

            wr.println("</table>");
        } else {
            // мой вариант (передавал параметры)
            //wr.println("id: " + req.getParameter("id") + "</br>name: " + req.getParameter("name"));
            // вариант преподавателя (парсил URL)
            Matcher matcher = PARAM_PATTERN.matcher( req.getPathInfo() );
            if(matcher.matches()) {
                long id = Long.parseLong( matcher.group(1) );
                User user = this.userRepository.findById( id );
                if(user == null) {
                    resp.getWriter().println("<p>User not found</p>");
                    resp.setStatus( 404 );
                    return;
                }
                resp.getWriter().println("<p>Id: " + user.getId() + "</p>");
                resp.getWriter().println("<p>Username: " + user.getUsername() + "</p>");
            }
            else
            {
                resp.getWriter().println("<p>Bad parameters</p>");
                resp.setStatus( 400 );
            }
        }
    }
}
