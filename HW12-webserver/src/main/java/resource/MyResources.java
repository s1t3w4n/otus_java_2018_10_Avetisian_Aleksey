package resource;

import model.User;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import service.UserService;
import service.UserServiceImpl;
import servlets.LoginServlet;
import servlets.MainServlet;


public class MyResources {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";
    private final UserService service;

    public MyResources() {
        service = addDBService();
    }

    public void fillResourcesAndStart() throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        addServlets(context);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new MainServlet()), "/start");
        context.addServlet(new ServletHolder(new LoginServlet(service)), "/login");
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }

}
