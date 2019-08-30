import resource.NewJettyServerWrapper;

public class FrontendClientMain {

    public static void main(String[] args) throws Exception {
        new NewJettyServerWrapper(Integer.valueOf(args[3]), Integer.valueOf(args[1])).fillResourcesAndStart();
    }
}
