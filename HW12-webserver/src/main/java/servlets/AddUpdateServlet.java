package servlets;

import model.User;
import resource.TemplateProcessor;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUpdateServlet extends HttpServlet {
    private static final String TEMPLATE = "add.html";
    private final TemplateProcessor templateProcessor;
    private final UserService userService;

    public AddUpdateServlet(UserService userService) {
        templateProcessor = new TemplateProcessor();
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String id = req.getParameter("id");

        userService.save(new User(name, password, Integer.parseInt(age), Long.parseLong(id)));
    }
}
