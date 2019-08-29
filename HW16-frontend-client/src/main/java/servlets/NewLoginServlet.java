package servlets;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import resource.TemplateProcessor;
import service.FrontendServiceFE;
import websockets.NewLoginWebSocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewLoginServlet extends WebSocketServlet {
    private static final String TEMPLATE = "login.html";
    private static final String STATUS = "status";
    private static final String DEFAULT_STATUS = "Type";
    private static final String COLOR = "color";
    private static final String DEFAULT_COLOR = "blue";

    private String status = DEFAULT_STATUS;
    private String color = DEFAULT_COLOR;

    private final TemplateProcessor templateProcessor;
    private final FrontendServiceFE frontendService;

    public NewLoginServlet(TemplateProcessor templateProcessor, FrontendServiceFE frontendService) {
        this.templateProcessor = templateProcessor;
        this.frontendService = frontendService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put(STATUS, status);
        variables.put(COLOR, color);

        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, variables));

        status = DEFAULT_STATUS;
        color = DEFAULT_COLOR;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String name = req.getParameter("name");


        if (Objects.nonNull(id)) {
            req.getSession();
            req.getSession(false).setAttribute("id", Long.parseLong(id, 10));
            req.getSession(false).setAttribute("name", name);
            color = "green";
            status = "Valid";
        } else {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
            status = "Invalid";
            color = "red";
        }
    }

    @Override
    public void configure (WebSocketServletFactory webSocketServletFactory){
        webSocketServletFactory.setCreator((servletUpgradeRequest, servletUpgradeResponse)
                -> new NewLoginWebSocket(frontendService));
    }
}
