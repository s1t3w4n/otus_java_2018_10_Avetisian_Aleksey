package servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import resource.TemplateProcessor;
import service.FrontendServiceFE;
import websockets.NewShowWebSocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class NewShowServlet extends WebSocketServlet {
    private static final String TEMPLATE = "show.html";

    private final TemplateProcessor templateProcessor;
    private final FrontendServiceFE frontendService;

    public NewShowServlet(TemplateProcessor templateProcessor, FrontendServiceFE frontendService) {
        this.templateProcessor = templateProcessor;
        this.frontendService = frontendService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(templateProcessor.getPage(TEMPLATE, Collections.emptyMap()));
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.setCreator((servletUpgradeRequest, servletUpgradeResponse)
                -> new NewShowWebSocket(frontendService));
    }
}
