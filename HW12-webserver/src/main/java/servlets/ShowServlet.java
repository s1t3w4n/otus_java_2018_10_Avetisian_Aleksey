package servlets;

import model.User;
import resource.TemplateProcessor;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowServlet extends HttpServlet {
    private static final String TEMPLATE = "show.html";
    private static final String TABLE = "table";

    private final UserService service;
    private final TemplateProcessor templateProcessor;

    public ShowServlet(UserService service, TemplateProcessor templateProcessor) {
        this.service = service;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = service.loadAll();

        Map<String, Object> variables = new HashMap<>();
        variables.put(TABLE, users);

        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, variables));
    }

}
