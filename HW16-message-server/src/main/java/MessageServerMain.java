import messageSystem.Address;
import runner.ProcessRunnerImpl;
import server.SocketMessageServer;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageServerMain {
    private static final String FRONTEND_START_COMMAND_0 = "java -jar ../otus_java_2018_10/HW16-frontend-client/target/HW16-frontend-client.jar -dbport 5051 -port 8080";
    private static final String FRONTEND_START_COMMAND_1 = "java -jar ../otus_java_2018_10/HW16-frontend-client/target/HW16-frontend-client.jar -dbport 5051 -port 8081";
    private static final String DATABASE_START_COMMAND = "java -jar ../otus_java_2018_10/HW16-database-client/target/HW16-database-client.jar -port 5051";
    private static final int CLIENT_START_DELAY_SEC = 5;


    public static void main(String[] args) throws Exception {
        new MessageServerMain().start();
    }

    private void start() throws Exception {
        SocketMessageServer server = new SocketMessageServer();
        System.out.println("Server started");

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        startClient(server, executorService, FRONTEND_START_COMMAND_0);
        startClient(server, executorService, FRONTEND_START_COMMAND_1);
        startClient(server, executorService, DATABASE_START_COMMAND);

        server.start();


        executorService.shutdown();
    }

    private void startClient(SocketMessageServer server, ScheduledExecutorService executorService, String command) {
        executorService.schedule(() -> {
            try {
                server.registerAddress(new Address(findAddress(command)));
                new ProcessRunnerImpl().start(command);
            } catch (IOException ignored) {

            }
        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);
    }

    private String findAddress(String command) {
        String[] args = command.split(" ");
        String address = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-port")) {
                address = args[i + 1];
            }
        }
        return address;
    }
}
