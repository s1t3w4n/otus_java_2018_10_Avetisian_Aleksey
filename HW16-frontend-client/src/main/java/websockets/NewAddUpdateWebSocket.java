package websockets;

import app.Message;
import app.frontend.websockets.MyWebSocket;
import com.google.gson.Gson;
import messages.AddMessage;
import model.User;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.FrontendServiceFE;

@WebSocket
public class NewAddUpdateWebSocket implements MyWebSocket {

    private final Integer id;
    private final FrontendServiceFE frontendService;

    public NewAddUpdateWebSocket(FrontendServiceFE frontendService) {
        this.frontendService = frontendService;
        id = frontendService.registerWebSocket(this);
    }
    @Override
    public void sendResponseMessage(String message) {
        //
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Gson gson = new Gson();
        User user = gson.fromJson(message, User.class);
        Message toDBMessage = new AddMessage(
                frontendService.getAddress(),
                frontendService.getDBAddress(),
                id,
                user);
        frontendService.sendRequest(toDBMessage);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }
}
