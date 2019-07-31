package app;


import app.frontend.websockets.MyWebSocket;
import messageSystem.Addressee;

public interface FrontendService extends Addressee {
    void init();

    int registerWebSocket(MyWebSocket myWebSocket);

    void sendResponse(int id, String message);

    MessageSystemContext getContext();

}

