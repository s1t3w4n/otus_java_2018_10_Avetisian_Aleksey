package servlets;

import resource.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private final TemplateProcessor templateProcessor;
    private final static String TEMPLATE = "admin.html";

    public AdminServlet() {
        templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,null));
    }
}
