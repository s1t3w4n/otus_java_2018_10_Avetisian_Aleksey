import resource.NewJettyServerWrapper;

public class FrontendClientMain {

    public static void main(String[] args) throws Exception {
        new NewJettyServerWrapper().fillResourcesAndStart();
        System.out.println("Frontend client started");
    }
}
