package app.frontend.websockets;

import app.FrontendService;
import app.messages.MsgAdd;
import com.google.gson.Gson;
import messageSystem.Message;
import model.User;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class AddUpdateWebSocket implements MyWebSocket {


    private final Integer id;
    private final FrontendService frontendService;

    public AddUpdateWebSocket(   FrontendService frontendService) {
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
        Message toDBMessage = new MsgAdd(
                frontendService.getAddress(),
                frontendService.getContext().getDbAddress(),
                id,
                user);
        frontendService.getMS().sendMessage(toDBMessage);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }
}
