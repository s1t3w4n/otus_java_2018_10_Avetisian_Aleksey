import resource.JettyServerWrapper;

public class FrontendClientMain {

    public static void main(String[] args) throws Exception {
        new JettyServerWrapper().fillResourcesAndStart();
    }
}
