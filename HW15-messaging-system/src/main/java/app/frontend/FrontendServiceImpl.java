package app.frontend;

import app.FrontendService;
import app.MessageSystemContext;
import app.frontend.websockets.MyWebSocket;
import messageSystem.Address;
import messageSystem.MessageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FrontendServiceImpl implements FrontendService {

    private final Address address;
    private final MessageSystemContext context;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private final Map<Integer, MyWebSocket> webSocketMap = new HashMap<>();

    public FrontendServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public int registerWebSocket(MyWebSocket myWebSocket) {
        int id = ID_COUNTER.incrementAndGet();
        webSocketMap.put(id, myWebSocket);
        return id;
    }

    @Override
    public void sendResponse(int id, String message) {
        webSocketMap.get(id).sendResponseMessage(message);
    }

    @Override
    public MessageSystemContext getContext() {
        return context;
    }


    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
