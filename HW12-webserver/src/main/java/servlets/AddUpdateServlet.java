package servlets;

import model.User;
import resource.TemplateProcessor;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUpdateServlet extends HttpServlet {
    private static final String TEMPLATE = "add.html";
    private final TemplateProcessor templateProcessor;
    private final UserService userService;
    private final static String DEFAULT_MESAGE = "Fill all fields";
    private final static String MESAGE = "mesage";
    private final static String DEFAULT_COLOR = "green";
    private final static String COLOR = "color";
    private String mesage;
    private String color;


    public AddUpdateServlet(UserService userService) {
        templateProcessor = new TemplateProcessor();
        this.userService = userService;
        mesage = DEFAULT_MESAGE;
        color = DEFAULT_COLOR;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> varieabels = new HashMap<>();
        varieabels.put(MESAGE, mesage);
        varieabels.put(COLOR, color);
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, varieabels));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String id = req.getParameter("id");
        List<String> values = new ArrayList<>();
        values.add(name);
        values.add(password);
        values.add(id);
        values.add(age);

        if (isFilled(values)) {
            if (isNumberic(id) && isNumberic(age)) {
                userService.save(new User(name, password, Integer.parseInt(age), Long.parseLong(id)));
                mesage = "Add another user";
                color = "#6A5ACD";
                doGet(req, resp);
                mesage = DEFAULT_MESAGE;
                color = DEFAULT_COLOR;
            } else {
                mesage = "User has not added! Id and Age must be a number!";
                color = "#FFA500";
                doGet(req, resp);
                mesage = DEFAULT_MESAGE;
                color = DEFAULT_COLOR;
            }
        } else {
            mesage = "User has not added! Fill all fields! ";
            color = "#DC143C";
            doGet(req, resp);
            mesage = DEFAULT_MESAGE;
            color = DEFAULT_COLOR;
        }


    }

    private boolean isNumberic(String str) {
        {
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }

    private boolean isFilled(List<String> values) {
        boolean isFilled = true;
        for (String value : values) {
            if (value.length() == 0) {
                isFilled = false;
            }
        }
        return isFilled;
    }
}
