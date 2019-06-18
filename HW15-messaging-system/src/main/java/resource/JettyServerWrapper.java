package resource;

import app.DBService;
import app.FrontendService;
import app.MessageSystemContext;
import app.db.DBServiceImpl;
import app.frontend.FrontendServiceImpl;
import app.frontend.servlets.AddUpdateServlet;
import app.frontend.servlets.LoginServlet;
import app.frontend.servlets.ShowServlet;
import filters.AdminFilter;
import messageSystem.Address;
import messageSystem.MessageSystem;
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
import servlets.AdminServlet;
import servlets.MainServlet;

public class JettyServerWrapper {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";
    private final UserService service;

    private final TemplateProcessor templateProcessor;
    private final MessageSystem messageSystem;
    private final MessageSystemContext context;
    private final Address frontAddress;
    private final Address dbAddress;
    private final FrontendService frontendService;
    private final DBService dbService;

    public JettyServerWrapper() {
        service = addDBService();
        templateProcessor = new TemplateProcessor();

        messageSystem = new MessageSystem();
        context = new MessageSystemContext(messageSystem);
        frontAddress = new Address("Frontend");
        dbAddress = new Address("DB");
        frontendService = new FrontendServiceImpl(context, frontAddress);
        dbService = new DBServiceImpl(context, dbAddress, service);
    }

    public void fillResourcesAndStart() throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        addFilters(context);
        addServlets(context);

        startMessageSystem();

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new MainServlet(templateProcessor)), "/main");
        context.addServlet(new ServletHolder(new AdminServlet(templateProcessor)), "/admin");
        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, frontendService)), "/login");
        context.addServlet(new ServletHolder(new ShowServlet(templateProcessor, frontendService)), "/show");
        context.addServlet(new ServletHolder(new AddUpdateServlet(templateProcessor, frontendService)), "/add");
    }

    private void addFilters(ServletContextHandler context) {
        context.addFilter(new FilterHolder(new AdminFilter()), "/admin", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/add", null);
        context.addFilter(new FilterHolder(new AdminFilter()), "/show", null);
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }

    private void startMessageSystem() {
        context.setFrontAddress(frontAddress);
        context.setDbAddress(dbAddress);
        frontendService.init();
        dbService.init();
        messageSystem.start();
    }

}
