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

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepository();
        this.userRepository.insert(new User("Alex"));
        this.userRepository.insert(new User("Petr"));
        this.userRepository.insert(new User("Felip"));
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

        if(req.getPathInfo().equals( "/")) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Username</th>");
            wr.println("</tr>");
            for (User user : userRepository.findAll()) {
                wr.println( "<tr>" );
                wr.println( "<td><a href='" + user.getId() + "?id=" + user.getId() + "&name=" + user.getUsername() + "'>" + user.getId() + "</a></td>" );
                wr.println( "<td>" + user.getUsername() + "</td>" );
                wr.println( "</tr>" );
            }
        } else wr.println("id: " + req.getParameter("id") + "</br>name: " + req.getParameter("name"));

        wr.println("</table>");
    }
}
