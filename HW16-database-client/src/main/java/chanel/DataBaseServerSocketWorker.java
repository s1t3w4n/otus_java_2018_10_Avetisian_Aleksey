package chanel;

import java.io.IOException;
import java.net.Socket;

public class DataBaseServerSocketWorker extends SocketMessageWorker {
    private final Socket socket;

    public DataBaseServerSocketWorker(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    private DataBaseServerSocketWorker(Socket socket) {
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
