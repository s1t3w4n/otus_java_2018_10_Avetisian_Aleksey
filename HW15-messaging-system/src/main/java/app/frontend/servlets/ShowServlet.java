package app.frontend.servlets;

import app.FrontendService;
import resource.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowServlet extends HttpServlet {
    private static final String TEMPLATE = "show.html";
    private static final String TABLE = "table";

    private final TemplateProcessor templateProcessor;
    private final FrontendService frontendService;

    public ShowServlet(TemplateProcessor templateProcessor, FrontendService frontendService) {
        this.templateProcessor = templateProcessor;
        this.frontendService = frontendService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        List<User> users = frontendService.handleShowRequest();
//
//        Map<String, Object> variables = new HashMap<>();
//        variables.put(TABLE, users);
//
//        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, variables));
    }

}
