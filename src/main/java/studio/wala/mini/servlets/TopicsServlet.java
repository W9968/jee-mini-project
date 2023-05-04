package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.TopicsController;
import studio.wala.mini.entities.Topic;
import studio.wala.mini.interfaces.TopicInterface;

import java.io.IOException;

@WebServlet(name = "TopicsServlet", value = "/TopicsServlet")
public class TopicsServlet extends HttpServlet {

    TopicInterface topics;

    @Override
    public void init() throws ServletException {
        topics = new TopicsController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }

        else if (action.equals("create")) {
            request.getRequestDispatcher("topic/manage.jsp").forward(request, response);
        }

        else if (action.equals("update")) {
            int topicId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("topic", topics.show(topicId));
            request.getRequestDispatcher("topic/manage.jsp").forward(request, response);
        }

        else if (action.equals("delete")) {
            int topicId = Integer.parseInt(request.getParameter("uid"));
            topics.destroy(topicId);
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }

        else if (action.equals("show")) {
            int topicId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("topic", topics.show(topicId));
            request.getRequestDispatcher("topic/show.jsp").forward(request, response);
        }

        else if (action.equals("search")) {
            String keyword = request.getParameter("value");
            request.setAttribute("topics", topics.index(keyword));
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }

        else {
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String topicName = request.getParameter("topicName");
        String topicDescription = request.getParameter("topicDescription");

        if (action.equals("store")) {
            topics.store(new Topic(topicName, topicDescription, null));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }

        if (action.equals("edit")) {
            int topicId = Integer.parseInt(request.getParameter("uid"));
            topics.update(new Topic(topicId, topicName, topicDescription, null));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }


    }
}
