package websockets;

import app.Message;
import app.frontend.websockets.MyWebSocket;
import messages.ShowMessage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.FrontendServiceFE;

import java.io.IOException;

@WebSocket
public class NewShowWebSocket implements MyWebSocket {
    private Session session;

    private final int id;
    private final FrontendServiceFE frontendService;

    public NewShowWebSocket(   FrontendServiceFE frontendService) {
        this.frontendService = frontendService;
        id = frontendService.registerWebSocket(this);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        System.out.println("Show " + message);
        Message toDBMessage = new ShowMessage(
                frontendService.getAddress(),
                frontendService.getDBAddress(),
                id);
        frontendService.sendRequest(toDBMessage);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @Override
    public void sendResponseMessage(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
