package servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import resource.TemplateProcessor;
import service.FrontendServiceFE;
import websockets.NewAddUpdateWebSocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewAddUpdateServlet extends WebSocketServlet {

    private static final String TEMPLATE = "add.html";
    private final static String DEFAULT_MESSAGE = "Fill all fields";
    private final static String MESSAGE = "message";
    private final static String DEFAULT_COLOR = "green";
    private final static String COLOR = "color";

    private final TemplateProcessor templateProcessor;
    private final FrontendServiceFE frontendService;

    public NewAddUpdateServlet(TemplateProcessor templateProcessor, FrontendServiceFE frontendService) {
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
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.setCreator((servletUpgradeRequest, servletUpgradeResponse)
                -> new NewAddUpdateWebSocket(frontendService));
    }
}
