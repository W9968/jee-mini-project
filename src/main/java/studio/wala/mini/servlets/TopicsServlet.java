    package studio.wala.mini.servlets;
    
    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import jakarta.servlet.annotation.*;
    import studio.wala.mini.controllers.TopicsController;
    import studio.wala.mini.entities.Topic;
    import studio.wala.mini.interfaces.TopicInterface;
    
    import java.io.File;
    import java.io.IOException;
    
    @WebServlet(name = "TopicsServlet", value = "/TopicsServlet")
    @MultipartConfig(location = "D:\\files\\java\\mini\\target\\mini-1.0-SNAPSHOT\\@config")
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
                request.setAttribute("topics", topics.paginate(1, 10));
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

            else if (action.equals("publish")) {
                int topicId = Integer.parseInt(request.getParameter("uid"));
                Boolean isPublished = Boolean.parseBoolean(request.getParameter("isPublish"));
                Topic topicIsBeingPublished = topics.show(topicId);
                topics.update(new Topic(topicId, topicIsBeingPublished.getTopicName(), topicIsBeingPublished.getTopicDescription(), topicIsBeingPublished.getTopicImage(), isPublished));
                request.setAttribute("topics", topics.index());
                request.getRequestDispatcher("topic/index.jsp").forward(request, response);
            }

            else if (action.equals("paginate")) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                Integer limit = Integer.parseInt(request.getParameter("limit"));
                request.setAttribute("topics", topics.paginate(page, limit));
                request.getRequestDispatcher("topic/index.jsp").forward(request, response);
            }

            else {
                request.setAttribute("topics", topics.paginate(1, 10));
                request.getRequestDispatcher("topic/index.jsp").forward(request, response);
            }
    
    
        }
    
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Get the action
            String action = request.getParameter("action");
    
            // setting folder upload path;
            String uploadFilePath = File.separator + "uploads";
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
    
            // Get the form data
            String topicName = request.getParameter("topicName");
            String topicDescription = request.getParameter("topicDescription");
    
            //Get all the parts from request and write it to the file on server
            Part part = request.getPart("topicImage");
            String fileName = part.getSubmittedFileName();
            part.write(  uploadFilePath + File.separator + fileName);
    
            if (action.equals("store")) {
                topics.store(new Topic(topicName, topicDescription, fileName, false));
                request.setAttribute("topics", topics.index());
                request.getRequestDispatcher("topic/index.jsp").forward(request, response);
            }
    
            if (action.equals("edit")) {
                int topicId = Integer.parseInt(request.getParameter("uid"));
                topics.update(new Topic(topicId, topicName, topicDescription, topics.show(topicId).getTopicImage(), topics.show(topicId).getTopicIsPublished()));
                request.setAttribute("topics", topics.index());
                request.getRequestDispatcher("topic/index.jsp").forward(request, response);
            }
    
        }
    
    }
