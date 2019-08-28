package server;

import app.Message;
import app.MessageWorker;
import messageSystem.Address;
import chanel.SocketMessageWorker;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

public class SocketMessageServer {

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;

    private final ExecutorService executor;
    private final BlockingQueue<Address> addresses;
    private final Map<Address, MessageWorker> workersMap;

    public SocketMessageServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        addresses = new LinkedBlockingQueue<>();
        workersMap = new ConcurrentHashMap<>();
    }

    public void start() throws Exception {
        executor.submit(this::echo);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            //logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                if (!addresses.isEmpty()) {
                    Socket socket = serverSocket.accept(); //blocks
                    SocketMessageWorker worker = new SocketMessageWorker(socket);
                    workersMap.put(addresses.take(), worker);
                }
            }
        }
    }

    public void registerAddress(Address address) {
        addresses.add(address);
    }



    @SuppressWarnings("InfiniteLoopStatement")
    private void echo() {
        while (true) {
            for (Map.Entry<Address, MessageWorker> entry : workersMap.entrySet()) {
                MessageWorker worker = entry.getValue();
                Message msg = worker.pool();
                while (msg != null) {
                    System.out.println("Sending the message from: " + msg.getFrom() +
                            "-> to : " + msg.getTo() + " message: " + msg.toString());
                    workersMap.get(msg.getTo()).send(msg);
                    msg = worker.pool();
                }
            }
        }
    }
}
