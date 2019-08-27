package service;

import app.FEService;
import app.Message;
import app.frontend.websockets.MyWebSocket;
import chanel.FrontendMessageSocketWorker;
import chanel.SocketMessageWorker;
import messageSystem.Address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FrontendService implements FEService {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    private final Address address;
    private final Address DBAddress;

    private final SocketMessageWorker worker;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private final Map<Integer, MyWebSocket> webSocketMap = new HashMap<>();

    public FrontendService(Address address, Address DBAddress) throws IOException {
        worker = new FrontendMessageSocketWorker(HOST, PORT);
        this.address = address;
        this.DBAddress = DBAddress;
        this.init(worker);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public Address getDBAddress() {
        return DBAddress;
    }

    @Override
    public int registerWebSocket(MyWebSocket webSocket) {
        int id = ID_COUNTER.incrementAndGet();
        webSocketMap.put(id, webSocket);
        return id;
    }

    @Override
    public void sendResponse(int id, String message) {
        webSocketMap.get(id).sendResponseMessage(message);
    }

    @Override
    public void sendRequest(Message message) {
        worker.send(message);
    }
}
