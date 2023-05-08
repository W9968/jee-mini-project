package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.UserController;
import studio.wala.mini.entities.User;
import studio.wala.mini.interfaces.UserInterface;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {

    UserInterface user;

    @Override
    public void init() throws ServletException {
        super.init();
        user = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null ) {
            response.sendRedirect("");
        } else {
            request.getRequestDispatcher("auth/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if( username == null || username.isEmpty() || password == null || password.isEmpty() )
            request.getRequestDispatcher("views/login.jsp").forward(request, response );
        else if (user.login(username, password) != null) {
            HttpSession session = request.getSession();
            User authedUser = user.login(username, password);
            session.setAttribute("user", authedUser);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("auth/login.jsp").forward(request, response );
        }


    }
}
