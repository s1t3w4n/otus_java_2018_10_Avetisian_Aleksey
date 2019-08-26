package service;

import app.frontend.websockets.MyWebSocket;
import chanel.FrontendMessageSocketWorker;
import messageSystem.Address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FrontendService {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    private final Address address;
    private final Address DBAddress;

    public Address getAddress() {
        return address;
    }

    public Address getDBAddress() {
        return DBAddress;
    }

    private final FrontendMessageSocketWorker frontendMessageSocketWorker;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private final Map<Integer, MyWebSocket> webSocketMap = new HashMap<>();

    public FrontendService(Address address, Address DBAddress) throws IOException {
        frontendMessageSocketWorker = new FrontendMessageSocketWorker(HOST, PORT);
        this.address = address;
        this.DBAddress = DBAddress;
    }

    public int registerWebSocket(MyWebSocket webSocket) {
        int id = ID_COUNTER.incrementAndGet();
        webSocketMap.put(id, webSocket);
        return id;
    }

    public void sendResponse(int id, String message) {
        webSocketMap.get(id).sendResponseMessage(message);
    }

   /* public void sendRequest(Message message) {
        frontendMessageSocketWorker.send(message);
    }

    public void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Message msg = frontendMessageSocketWorker.take();
                    System.out.println("Message received: " + msg.toString());
                    msg.exec(this);
                }
            } catch (InterruptedException e) {
            }
        });
    }*/
}
