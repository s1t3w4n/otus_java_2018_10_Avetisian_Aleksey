package app.frontend.websockets;

import app.FrontendService;
import app.db.Auth;
import app.messages.MsgLogin;
import com.google.gson.Gson;
import messageSystem.Message;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class LoginWebSocket implements MyWebSocket {

    private Session session;

    private final Integer id;
    private final FrontendService frontendService;

    public LoginWebSocket(FrontendService frontendService) {
        this.frontendService = frontendService;
        id = frontendService.handleRequest(this);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Gson gson = new Gson();
        Auth auth = gson.fromJson(message, Auth.class);
        Message toDBMessage = new MsgLogin(
                frontendService.getAddress(),
                frontendService.getContext().getDbAddress(),
                id,
                auth.getId(),
                auth.getPassword());
        frontendService.getMS().sendMessage(toDBMessage);
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
