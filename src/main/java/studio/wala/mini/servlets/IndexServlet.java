package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.TopicsController;
import studio.wala.mini.interfaces.TopicInterface;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "")
public class IndexServlet extends HttpServlet {

    TopicInterface topics;

    @Override
    public void init() throws ServletException {
        topics = new TopicsController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
