package studio.wala.mini.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import studio.wala.mini.controllers.ChoiceController;
import studio.wala.mini.controllers.QuestionController;
import studio.wala.mini.controllers.TopicsController;
import studio.wala.mini.entities.Choice;
import studio.wala.mini.interfaces.ChoiceInterface;
import studio.wala.mini.interfaces.QuestionInterface;
import studio.wala.mini.interfaces.TopicInterface;

import java.io.IOException;

@WebServlet(name = "ChoicesServlet", value = "/ChoicesServlet")
public class ChoicesServlet extends HttpServlet {

    ChoiceInterface choices;
    QuestionInterface questions;
    TopicInterface topics;

    @Override
    public void init() throws ServletException {
        choices = new ChoiceController();
        questions = new QuestionController();
        topics = new TopicsController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null ) {
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        else if (action.equals("create")) {
            request.setAttribute("question", request.getParameter("question") );
            request.getRequestDispatcher("choice/manage.jsp").forward(request, response);
        }

        else if (action.equals("update")) {
            int choiceId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("choice", choices.show(choiceId));
            request.getRequestDispatcher("choice/manage.jsp").forward(request, response);
        } else {
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }

        // response.sendRedirect("ChoicesServlet?action=create");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Get the form data
        String content = request.getParameter("content");
        Boolean isCorrect = Boolean.parseBoolean(request.getParameter("correct"));
        int questionId = Integer.parseInt(request.getParameter("question_id"));

        if (action.equals("store")) {
            choices.store(new Choice(content, isCorrect, questions.show(questionId)));
            request.setAttribute("questions", questions.paginate(1,10));
            request.setAttribute("topics", topics.index());
            request.getRequestDispatcher("question/index.jsp").forward(request, response);
        }
    }
}
