import resource.JettyServerWrapper;

public class FrontendClientMain {

    public static void main(String[] args) throws Exception {
        JettyServerWrapper jettyServerWrapper = new JettyServerWrapper(8080, 5051);
        jettyServerWrapper.fillResourcesAndStart();
    }
}
