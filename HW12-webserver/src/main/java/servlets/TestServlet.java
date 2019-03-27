package servlets;

import resource.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class TestServlet extends HttpServlet {
    private static final String TEMPLATE = "login.html";
    private static final TemplateProcessor templateProcessor = new TemplateProcessor();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,new HashMap<>()));
    }
}
