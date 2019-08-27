import runner.ProcessRunnerImpl;
import server.SocketMessageServer;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageServerMain {
    private static final String FRONTEND_START_COMMAND = "java -jar ../otus_java_2018_10/HW16-frontend-client/target/HW16-frontend-client.jar -port 8080";
    private static final String DATABASE_START_COMMAND = "java -jar ../otus_java_2018_10/HW16-database-client/target/HW16-database-client.jar -port 5051";
    private static final int CLIENT_START_DELAY_SEC = 5;


    public static void main(String[] args) throws Exception {
        new MessageServerMain().start();
    }

    private void start() throws Exception {
        SocketMessageServer server = new SocketMessageServer();
        System.out.println("Server started");

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        startClient(executorService, FRONTEND_START_COMMAND, server);
        startClient(executorService, DATABASE_START_COMMAND, server);


        server.start();

        executorService.shutdown();
    }

    private void startClient(ScheduledExecutorService executorService, String command, SocketMessageServer server) {
        executorService.schedule(() -> {
            try {
                new ProcessRunnerImpl(server).start(command);
            } catch (IOException ignored) {

            }
        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);
    }
}
