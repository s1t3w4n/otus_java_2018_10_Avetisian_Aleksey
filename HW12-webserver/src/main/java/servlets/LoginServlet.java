package servlets;


import model.User;
import org.eclipse.jetty.http.HttpStatus;
import resource.TemplateProcessor;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    private static final String TEMPLATE = "login.html";
    private static final String STATUS = "status";
    private static final String DEFAULT_STATUS = "Type";
    private static final String COLOR = "color";
    private static final String DEFAULT_COLOR = "blue";

    private final UserService service;
    private final TemplateProcessor templateProcessor;


    public LoginServlet(UserService service) {
        this.service = service;
        templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String status = DEFAULT_STATUS;
        String color = DEFAULT_COLOR;

        if(Objects.nonNull(id)) {
            User user = service.load(Long.parseLong(id, 10));
            if (Objects.nonNull(user) && user.getPassword().equals(password)) {
                req.getSession();
                req.getSession(false).setAttribute("id", user.getId());
                req.getSession(false).setAttribute("name", user.getName());
                status = "Valid";
                color = "green";
            } else {
                resp.setStatus(HttpStatus.FORBIDDEN_403);
                status = "Invalid";
                color = "red";
            }
        }

        Map<String,Object> variables = new HashMap<>();
        variables.put(STATUS,status);
        variables.put(COLOR,color);
        /*//variables.put(RESENT_LOGIN, DEFAULT);
        resp.setContentType("text/json;charset=utf-8");*/
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,variables));
    }
}
