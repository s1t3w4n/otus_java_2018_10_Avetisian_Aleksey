package app;

import app.frontend.websockets.MyWebSocket;
import messageSystem.Address;

public interface ServiceFE extends Service {
    Address getDBAddress();

    int registerWebSocket(MyWebSocket webSocket);
    void sendResponse(int id, String message);
    void sendRequest(Message message);
}
