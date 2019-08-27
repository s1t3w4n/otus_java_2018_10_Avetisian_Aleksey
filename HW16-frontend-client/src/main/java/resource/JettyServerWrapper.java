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
import service.FrontendService;
import servlets.AdminServlet;
import servlets.LoginServlet;
import servlets.MainServlet;

import java.io.IOException;

public class JettyServerWrapper {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";

    private final TemplateProcessor templateProcessor;
    private final FrontendService frontendService;

    public JettyServerWrapper() throws IOException {

        templateProcessor = new TemplateProcessor();

//        Address frontAddress1 = new Address(Integer.toString(frontAddress));
//        Address dbAddress1 = new Address(Integer.toString(dbAddress));

//        Address frontAddress = new Address("8080");
//        Address dbAddress = new Address("5051");

        frontendService = new FrontendService(new Address("8080"), new Address("5051"));
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
        server.join();
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new AdminServlet(templateProcessor)), "/admin");
        context.addServlet(new ServletHolder(new MainServlet(templateProcessor)), "/main");
        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, frontendService)), "/login");
//        context.addServlet(new ServletHolder(new ShowServlet(templateProcessor, frontendService)), "/show");
//        context.addServlet(new ServletHolder(new AddUpdateServlet(templateProcessor, frontendService)), "/add");
    }

    private void addFilters(ServletContextHandler context) {
        context.addFilter(new FilterHolder(new AdminFilter()), "/admin", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/add", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/show", null);
    }

}
