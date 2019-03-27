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

    private static final String RESENT_LOGIN = "id";
    private static final String DEFAULT = "UNKNOWN";
    private static final String TEMPLATE = "login.html";

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
        if(Objects.nonNull(id)) {
            User user = service.load(Long.parseLong(id, 10));
            if (Objects.nonNull(user) && user.getPassword().equals(password)) {
                req.getSession();
            } else {
                resp.setStatus(HttpStatus.FORBIDDEN_403);
            }
        }

        Map<String,Object> variables = new HashMap<>();
        /*//variables.put(RESENT_LOGIN, DEFAULT);
        resp.setContentType("text/json;charset=utf-8");*/
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,variables));
    }
}
