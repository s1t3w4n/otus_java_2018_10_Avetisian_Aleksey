package app;


import app.frontend.websockets.MyWebSocket;
import messageSystem.Addressee;

public interface FrontendService extends Addressee {
    void init();

    Integer handleRequest(MyWebSocket myWebSocket);

    MyWebSocket sendResponse(Integer id);

    MessageSystemContext getContext();

}

