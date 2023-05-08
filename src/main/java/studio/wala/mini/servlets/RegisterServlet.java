package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.UserController;
import studio.wala.mini.entities.User;
import studio.wala.mini.interfaces.UserInterface;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    UserInterface users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("");
        } else {
            request.getRequestDispatcher("auth/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (users.register(new User(username, password, email, null, "ROLE_USER"))) {
            response.sendRedirect("AuthServlet");
        } else {
            request.setAttribute("error", "Username or email already exists");
            request.getRequestDispatcher("auth/register.jsp").forward(request, response);
        }

    }
}
