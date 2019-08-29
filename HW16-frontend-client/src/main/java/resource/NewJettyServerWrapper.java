package resource;

import filters.AdminFilter;
import messageSystem.Address;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import service.FrontendServiceFE;
import servlets.*;

import java.io.IOException;

public class NewJettyServerWrapper {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";

    private final TemplateProcessor templateProcessor;
    private final FrontendServiceFE frontendService;

    public NewJettyServerWrapper(int frontAddress, int dbAddress) throws IOException {

        templateProcessor = new TemplateProcessor();

        Address frontAddress1 = new Address(Integer.toString(frontAddress));
        Address dbAddress1 = new Address(Integer.toString(dbAddress));

        frontendService = new FrontendServiceFE(frontAddress1, dbAddress1);
    }

    public void fillResourcesAndStart() throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        addServlets(context);
        addFilters(context);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        System.out.println("Frontend client started");
        server.join();
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new AdminServlet(templateProcessor)), "/admin");
        context.addServlet(new ServletHolder(new MainServlet(templateProcessor)), "/main");
        context.addServlet(new ServletHolder(new NewLoginServlet(templateProcessor, frontendService)), "/login");
        context.addServlet(new ServletHolder(new NewShowServlet(templateProcessor, frontendService)), "/show");
        context.addServlet(new ServletHolder(new NewAddUpdateServlet(templateProcessor, frontendService)), "/add");
    }

    private void addFilters(ServletContextHandler context) {
        context.addFilter(new FilterHolder(new AdminFilter()), "/admin", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/add", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/show", null);
    }

}
