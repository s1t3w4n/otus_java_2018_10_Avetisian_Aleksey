package servlets;

import resource.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainServlet extends HttpServlet {
    private static final String TEMPLATE = "main.html";
    private static final String NAME = "name";
    private static final String DEFAULT_NAME_VALUE = "Guest";

    private final TemplateProcessor templateProcessor;

    public MainServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String nameValue;
        if (Objects.nonNull(session)) {
            nameValue = (String) session.getAttribute("name");
        } else {
            nameValue = DEFAULT_NAME_VALUE;
        }
        Map<String,Object> variables = new HashMap<>();
        variables.put(NAME, nameValue);
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,variables));
    }
}
