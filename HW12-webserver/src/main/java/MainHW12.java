import resource.JettyServerWrapper;
public class MainHW12 {
    public static void main(String[] args) throws Exception {

        JettyServerWrapper resources = new JettyServerWrapper();
        resources.fillResourcesAndStart();
    }
}
