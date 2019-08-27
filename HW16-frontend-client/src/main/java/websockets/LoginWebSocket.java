package websockets;

import app.Message;
import app.db.Auth;
import app.frontend.websockets.MyWebSocket;
import com.google.gson.Gson;
import messages.LoginMessage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.FrontendService;

import java.io.IOException;

@WebSocket
public class LoginWebSocket implements MyWebSocket {

    private Session session;

    private final Integer id;
    private final FrontendService frontendService;

    public LoginWebSocket(FrontendService frontendService) {
        this.frontendService = frontendService;
        id = frontendService.registerWebSocket(this);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Gson gson = new Gson();
        Auth auth = gson.fromJson(message, Auth.class);
        Message toDBMessage = new LoginMessage(
                frontendService.getAddress(),
                frontendService.getDBAddress(),
                id,
                auth.getId(),
                auth.getPassword());
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
