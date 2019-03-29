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

    public ShowServlet(UserService service) {
        this.service = service;
        templateProcessor  = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = service.loadAll();
        String table = bulidTable(users);

        Map<String,Object> variables = new HashMap<>();
        variables.put(TABLE,table);

        resp.getWriter().println(templateProcessor.getPage(TEMPLATE,variables));
    }

    private String bulidTable(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder("<table border = 3px>");
        stringBuilder.append("<tr><h2><b><td>NAME</td><td>ID</td><td>AGE</td></b></h2>");
        for (User user : users) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>");
            stringBuilder.append(user.getName());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(user.getId());
            stringBuilder.append("</td>");
            stringBuilder.append("<td>");
            stringBuilder.append(user.getAge());
            stringBuilder.append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }
}
