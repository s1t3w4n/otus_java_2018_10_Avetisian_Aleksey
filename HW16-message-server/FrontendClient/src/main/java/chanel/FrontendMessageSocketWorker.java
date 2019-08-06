package chanel;

import java.io.IOException;
import java.net.Socket;

public class FrontendMessageSocketWorker extends SocketMessageWorker {

    private final Socket socket;

    public FrontendMessageSocketWorker(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    private FrontendMessageSocketWorker(Socket socket) {
        super(socket);
        this.socket = socket;
    }

    public void close() {
        try {
            super.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
