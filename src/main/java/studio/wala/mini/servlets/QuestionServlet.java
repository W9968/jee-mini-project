package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.QuestionController;
import studio.wala.mini.controllers.TopicsController;
import studio.wala.mini.entities.Question;
import studio.wala.mini.interfaces.QuestionInterface;
import studio.wala.mini.interfaces.TopicInterface;

import java.io.IOException;

@WebServlet(name = "QuestionServlet", value = "/QuestionServlet")
public class QuestionServlet extends HttpServlet {

    QuestionInterface questions;
    TopicInterface topics;

    @Override
    public void init() throws ServletException {
        questions = new QuestionController();
        topics = new TopicsController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null) {
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        else if (action.equals("create")) {
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/manage.jsp").forward(request, response);
        }

        else if (action.equals("update")) {
            int questionId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("question", questions.show(questionId));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/manage.jsp").forward(request, response);
        }

        else if (action.equals("show")) {
            int questionId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("question", questions.show(questionId));
            request.getRequestDispatcher("question/show.jsp").forward(request, response);
        }

        else if (action.equals("search")) {
            String keyword = request.getParameter("value");
            request.setAttribute("questions", questions.index(keyword));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        else if (action.equals("delete")) {
            int questionId = Integer.parseInt(request.getParameter("uid"));
            questions.destroy(questionId);
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        else if (action.equals("paginate")) {
            Integer page = Integer.parseInt(request.getParameter("page"));
            Integer limit = Integer.parseInt(request.getParameter("limit"));
            request.setAttribute("questions", questions.paginate(page, limit));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("topic/index.jsp").forward(request, response);
        }

        else if (action.equals("filter")) {
            Integer topicId = Integer.parseInt(request.getParameter("topicId"));
            if (topicId == 0) request.setAttribute("questions", questions.paginate(1,10));
            else request.setAttribute("questions", questions.filter(topicId));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        else {
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action
        String action = request.getParameter("action");

        // Get the form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int topicId = Integer.parseInt(request.getParameter("topic_id"));

        if (action.equals("store")) {
            questions.store(new Question(title, description, topics.show(topicId)));
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        if (action.equals("edit")) {
            int questionId = Integer.parseInt(request.getParameter("uid"));
            questions.update(new Question(questionId, title, description, topics.show(topicId)));
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }
    }
}
