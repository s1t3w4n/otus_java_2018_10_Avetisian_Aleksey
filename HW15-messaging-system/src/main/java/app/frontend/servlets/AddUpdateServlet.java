package app.frontend.servlets;

import app.FrontendService;
import model.User;
import resource.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUpdateServlet extends HttpServlet {
    private static final String TEMPLATE = "add.html";
    private final static String DEFAULT_MESSAGE = "Fill all fields";
    private final static String MESSAGE = "message";
    private final static String DEFAULT_COLOR = "green";
    private final static String COLOR = "color";


    private final TemplateProcessor templateProcessor;
    private final FrontendService frontendService;


    public AddUpdateServlet(TemplateProcessor templateProcessor, FrontendService frontendService) {
        this.templateProcessor = templateProcessor;
        this.frontendService = frontendService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put(MESSAGE, DEFAULT_MESSAGE);
        variables.put(COLOR, DEFAULT_COLOR);
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, variables));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> values = fillValues(req);
        Map<String, Object> variables = new HashMap<>();
        String message;
        String color;
//        if (isFilled(values)) {
//            if (isNumeric(values.get("id")) && isNumeric(values.get("age"))) {
//                frontendService.add(new User(values.get("name"),
//                        values.get("password"),
//                        Integer.parseInt(values.get("age")),
//                        Long.parseLong(values.get("id"))));
//                message = "Add another user";
//                color = "#6A5ACD";
//            } else {
//                message = "User has not added! Id and Age must be a number!";
//                color = "#FFA500";
//            }
//        } else {
//            message = "User has not added! Fill all fields! ";
//            color = "#DC143C";
//        }
//        variables.put(MESSAGE, message);
//        variables.put(COLOR, color);
//        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, variables));
    }

    private Map<String, String> fillValues(HttpServletRequest req) {
        Map<String, String> values = new HashMap<>();
        values.put("name", req.getParameter("name"));
        values.put("password", req.getParameter("password"));
        values.put("age", req.getParameter("age"));
        values.put("id", req.getParameter("id"));
        return values;
    }

    private boolean isNumeric(String str) {
        {
            try {
                Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }

    private boolean isFilled(Map<String, String> values) {
        boolean isFilled = true;
        for (String value : values.values()) {
            if (value.length() == 0) {
                isFilled = false;
            }
        }
        return isFilled;
    }
}
