package server;

import app.MessageWorker;
import messageSystem.Address;
import chanel.SocketMessageWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

public class SocketMessageServer {

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;

    private final ExecutorService executor;
    private final ServerSocket serverSocket;
    private final Map<Address, MessageWorker> workersMap;

    public SocketMessageServer() throws IOException {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        workersMap = new ConcurrentHashMap<>();
        serverSocket = new ServerSocket(PORT);
    }

    public void start() {
        executor.submit(this::echo);

    }

    public void registerClientSocket(Address address) throws IOException {
        if (!executor.isShutdown()) {
            Socket socket = serverSocket.accept();
            SocketMessageWorker worker = new SocketMessageWorker(socket);
            workersMap.put(address, worker);
            System.out.println("Worker started" + address.getId());
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void echo() {
        /*while (true) {
            for (Map.Entry<Address, MessageWorker> entry: workersMap.entrySet()) {
                MessageWorker worker = entry.getValue();
                Message msg = worker.pool();
                while (msg != null) {
                    System.out.println("Mirroring the message: " + msg.toString());
                    workersMap.get(msg.getTo()).send(msg);
                    msg = worker.pool();
                }
            }
        }*/
    }
}
