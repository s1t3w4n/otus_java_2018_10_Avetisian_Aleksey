package resource;

import filters.AdminFilter;
import model.User;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import service.UserService;
import service.UserServiceImpl;
import servlets.*;


public class JettyServerWrapper {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";
    private final UserService service;
    private final TemplateProcessor templateProcessor;

    public JettyServerWrapper() {
        service = addDBService();
        templateProcessor = new TemplateProcessor();
    }

    public void fillResourcesAndStart() throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        addFilters(context);
        addServlets(context);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new MainServlet(templateProcessor)), "/main");
        context.addServlet(new ServletHolder(new LoginServlet(service, templateProcessor)), "/login");
        context.addServlet(new ServletHolder(new AdminServlet(templateProcessor)), "/admin");
        context.addServlet(new ServletHolder(new AddUpdateServlet(service, templateProcessor)), "/add");
        context.addServlet(new ServletHolder(new ShowServlet(service,templateProcessor)), "/show");
    }

    private void addFilters(ServletContextHandler context) {
        context.addFilter(new FilterHolder(new AdminFilter()),"/admin", null);
        context.addFilter(new FilterHolder(new AdminFilter()),"/add", null);
        context.addFilter(new FilterHolder(new AdminFilter()),"/show", null);
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }

}
